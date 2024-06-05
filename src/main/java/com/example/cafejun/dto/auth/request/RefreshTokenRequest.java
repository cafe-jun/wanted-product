package com.example.cafejun.dto.auth.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank
    @NotNull
    private String refreshToken;
    public RefreshTokenRequest(){}
    @Builder
    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
