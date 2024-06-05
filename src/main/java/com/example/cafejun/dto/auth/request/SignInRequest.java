package com.example.cafejun.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SignInRequest {

    @NotBlank
    @NotNull
    @Email
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @NotBlank
    @NotNull
    @Schema(description = "비밀번호", example = "string")
    private String password;
}
