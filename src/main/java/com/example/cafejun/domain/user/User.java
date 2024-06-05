package com.example.cafejun.domain.user;

import com.example.cafejun.repository.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class User {

    private Long id;

    private String email;
    @Setter
    private String password;

    private Provider provider;

    private Role role;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
