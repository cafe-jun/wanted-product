package com.example.cafejun.service.auth;

import com.example.cafejun.common.error.TokenErrorCode;
import com.example.cafejun.common.exception.ApiException;
import com.example.cafejun.config.security.token.UserPrincipal;
import com.example.cafejun.domain.user.TokenMapping;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CustomTokenProviderService {
//    @Autowired
//    private OAuth2Config oAuth2Config;
String secretKey = "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHaVlwEyVPQye08f7MGVA9XkHa08f7MGVA9XkHa08f7MGVA9XkHa08f7MGVA9XkHa";
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    public TokenMapping refreshToken(Authentication authentication, String refreshToken) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime()+3000);
        Key key = getKey();
        String accessToken = Jwts.builder()
                            .setSubject(Long.toString(userPrincipal.getId()))
                            .setIssuedAt(new Date())
                            .setExpiration(accessTokenExpiresIn)
                            .signWith(key, SignatureAlgorithm.HS512).compact();

        return TokenMapping.builder()
                .email(userPrincipal.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    public TokenMapping createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime()+ 3600000);
        Date refreshTokenExpiresIn = new Date(now.getTime() + 600000);
        Key key = getKey();
        String accessToken = Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenMapping.builder()
                .email(userPrincipal.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Long getMemberIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
    public UsernamePasswordAuthenticationToken getAuthenticationById(String token) {
        Long memberId = getMemberIdFromToken(token);
        log.info("signin member id ,{}",memberId);
        UserDetails userDetails = customUserDetailsService.loadUserById(memberId);
        log.info("UserDetails,{},{},{}",userDetails.getAuthorities(),userDetails.getPassword(),userDetails.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        log.info("UsernamePasswordAuthenticationToken,{}",authentication.getAuthorities());
        return authentication;
    }
    public UsernamePasswordAuthenticationToken getAuthenticationByEmail(String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        return authentication;
    }

    public Long getExpiration(String token) {
        // accessToken 남은 유효시간
        Date expiration = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        //시간 계산
        return (expiration.getTime()-now);
    }

    public Map<String, Object> validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
//            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            var result = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());
        } catch (SecurityException | MalformedJwtException ex) {
            log.error("잘못된 JWT 서명입니다.");
            throw new ApiException(TokenErrorCode.INVALID_TOKEN,ex);
        } catch (ExpiredJwtException ex) {
            log.error("만료된 JWT 토큰입니다.");
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN,ex);
        } catch (UnsupportedJwtException ex) {
            log.error("지원되지 않는 JWT 토큰입니다.");
            throw new ApiException(TokenErrorCode.UNSUPPORT_TOKEN,ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT 토큰이 잘못되었습니다.");
            throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION,ex);
        }
    }
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
