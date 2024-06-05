package com.example.cafejun.service.auth;

import com.example.cafejun.config.security.token.UserPrincipal;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.repository.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserReader userReader;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userReader.findByEmail(email,true);
        return UserPrincipal.create(user);
    }
    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) {
        User user = userReader.findById(id,true);
        log.debug("user.get() message", user.toString());
        return UserPrincipal.create(user);

    }
}
