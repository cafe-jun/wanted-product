package com.example.cafejun.repository.user;


import com.example.cafejun.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User findByEmail(String email,boolean isEmpty) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(isEmpty) {
            userEntity.orElseThrow(() -> new NotFoundException("유저 정보를 찾을 수 없습니다."));
        }
        log.info("userEntity : {}",userEntity.toString());
        return userEntity.get().toDomain();
    }
    public User findById(Long id,boolean isEmpty) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        userEntity.orElseThrow(() -> new NotFoundException("유저 정보를 찾을 수 없습니다."));
        return userEntity.get().toDomain();
    }
}
