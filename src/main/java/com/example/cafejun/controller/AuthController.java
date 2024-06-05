package com.example.cafejun.controller;

import com.example.cafejun.dto.auth.request.SignInDto;
import com.example.cafejun.dto.auth.request.SignInRequest;
import com.example.cafejun.dto.auth.request.SignUpRequest;
import com.example.cafejun.service.UserService;
import com.example.cafejun.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Auth", description = "로그인, 로그아웃, 회원가입 API")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController()
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    public ResponseEntity<?> login(@RequestBody @Valid SignInRequest dto) {
        return authService.signin(dto);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    public String logout() {
        return "logout";
    }


    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signUpDto) {
        return authService.signup(signUpDto);
    }
}
