package com.example.cafejun.dto.auth.request;

import com.example.cafejun.domain.user.User;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String password_confirmation;

    public User toUser() {
        return User.builder().email(email).password(password).build();
    }
}
