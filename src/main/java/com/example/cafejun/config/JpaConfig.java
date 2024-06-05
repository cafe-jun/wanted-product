//package com.example.cafejun.config;
//
//
//import com.example.cafejun.domain.user.User;
//import com.example.cafejun.dto.security.ProductPrincipal;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContext;
//
//import java.util.Optional;
//
//@EnableJpaAuditing
//@Configuration
//public class JpaConfig {
//
//    @Bean
//    public AuditorAware<String> auditorAware() {
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(ProductPrincipal.class::cast)
//                .map(ProductPrincipal::getUsername);
//    }
//}
