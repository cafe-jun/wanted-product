package com.example.cafejun.service.auth;

import com.example.cafejun.advise.assertThat.DefaultAssert;
import com.example.cafejun.domain.user.*;
import com.example.cafejun.dto.auth.request.RefreshTokenRequest;
import com.example.cafejun.dto.auth.request.SignInRequest;
import com.example.cafejun.dto.auth.request.SignUpRequest;
import com.example.cafejun.dto.response.ApiResponse;
import com.example.cafejun.dto.response.AuthResponse;
import com.example.cafejun.dto.response.Message;
import com.example.cafejun.repository.TokenRepository;
import com.example.cafejun.repository.user.UserRepository;
import com.example.cafejun.service.auth.CustomTokenProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomTokenProviderService customTokenProviderService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public ResponseEntity<?> signin(SignInRequest signInRequest){
        log.debug("authentication,{}",signInRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );
        log.debug("authentication,{}",authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenMapping tokenMapping = customTokenProviderService.createToken(authentication);
        Token token = Token.builder()
                .refreshToken(tokenMapping.getRefreshToken())
                .email(tokenMapping.getEmail())
                .build();
        tokenRepository.save(token);
        AuthResponse authResponse = AuthResponse.builder().accessToken(tokenMapping.getAccessToken()).refreshToken(token.getRefreshToken()).build();
        return ResponseEntity.ok(authResponse);
    }

    public ResponseEntity<?> signup(SignUpRequest signUpRequest) {
//        DefaultAssert.isEqualString(signUpRequest.getPassword(), signUpRequest.getPassword_confirmation(),"패스워드가 일치하지 않습니다.");
        DefaultAssert.isTrue(!userRepository.existsByEmail(signUpRequest.getEmail()),"해당 이메일이 이미 존재합니다.");
        User user = signUpRequest.toUser();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user.toEntity());
//        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/")
//                .buildAndExpand(user.getId()).toUri();
        ApiResponse apiResponse = ApiResponse.builder().check(true).information(Message.builder().message("회원가입에 성공했습니다.").build()).build();
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> signout(RefreshTokenRequest tokenRefreshRequest) {
        boolean checkValid = valid(tokenRefreshRequest.getRefreshToken());
        DefaultAssert.isAuthentication(checkValid);
        Optional<Token> token = tokenRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken());
        tokenRepository.delete(token.get());
        ApiResponse apiResponse = ApiResponse.builder().check(true).information(Message.builder().message("로그아웃 하였습니다.")).build();
        return ResponseEntity.ok(apiResponse);
    }


    public ResponseEntity<?> refresh(RefreshTokenRequest tokenRefreshRequest) {
        // 1차 검증
        boolean checkValid = valid(tokenRefreshRequest.getRefreshToken());
        DefaultAssert.isAuthentication(checkValid);
        Optional<Token> token = tokenRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken());
        Authentication authentication = customTokenProviderService.getAuthenticationByEmail(token.get().getEmail());
        //4. refresh token 정보 값을 업데이트 한다
        //시간 유호성 확인
        TokenMapping tokenMapping;
        Long expirationTime = customTokenProviderService.getExpiration(tokenRefreshRequest.getRefreshToken());
        if(expirationTime >0) {
            tokenMapping = customTokenProviderService.refreshToken(authentication,token.get().getRefreshToken());
        } else {
            tokenMapping = customTokenProviderService.createToken((authentication));
        }
        Token updateToken = token.get().updateRefreshToken(tokenMapping.getRefreshToken());
        tokenRepository.save(updateToken);
        AuthResponse authResponse = AuthResponse.builder().accessToken(tokenMapping.getAccessToken()).refreshToken(updateToken.getRefreshToken()).build();
        return ResponseEntity.ok(authResponse);
    }

    private boolean valid(String refreshToken) {
        // 1. 토큰 형식 물리적 검증
        boolean validateCheck = customTokenProviderService.validateToken(refreshToken);
        DefaultAssert.isTrue(validateCheck,"Token 검증에 실패하였습니다.");
        // 2. refresh token 값을 불러온다.
        Optional<Token> token = tokenRepository.findByRefreshToken(refreshToken);
        DefaultAssert.isTrue(token.isPresent(),"탈퇴 처리된 회원입니다.");
        //3. email 값을 통해 인증값을 불러온다
        Authentication authentication = customTokenProviderService.getAuthenticationByEmail(token.get().getEmail());
        DefaultAssert.isTrue(token.get().getEmail().equals(authentication.getName()),"사용자 인증에 실패하였습니다.");
        return true;
    }
}
