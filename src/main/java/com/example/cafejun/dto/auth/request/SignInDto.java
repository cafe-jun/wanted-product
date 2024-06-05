package com.example.cafejun.dto.auth.request;

import com.example.cafejun.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "로그인 DTO",title = "로그인 DTO")
public class SignInDto {

    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @Schema(description = "비밀번호", example = "string")
    private String password;

    public User toUser() {
        return User.builder().email(email).password(password).build();
    }
}
