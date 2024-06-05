package com.example.cafejun.service;

import com.example.cafejun.domain.user.User;
import com.example.cafejun.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public ResponseEntity<?> saveUser(User user) {
        userRepository.save(user.toEntity());
        return null;
    }
}
