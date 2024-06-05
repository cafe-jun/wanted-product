package com.example.cafejun.domain.user;
import lombok.Builder;
import lombok.Data;

@Data
public class TokenMapping {
    private String email;
    private String accessToken;
    private String refreshToken;

    public TokenMapping() {}

    @Builder
    public TokenMapping(String email, String accessToken, String refreshToken) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
