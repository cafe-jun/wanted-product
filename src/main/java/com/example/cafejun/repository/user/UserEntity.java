package com.example.cafejun.repository.user;

import com.example.cafejun.domain.user.Provider;
import com.example.cafejun.domain.user.Role;
import com.example.cafejun.domain.user.User;
import com.example.cafejun.repository.AuditField;
import com.example.cafejun.repository.DefaultTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


@Data
@Table(indexes = {
        @Index(columnList = "email")},
        name = "users"
)
@Entity
@NoArgsConstructor
public class UserEntity extends DefaultTime {
    //해결방법 - 매핑전략 바꾸기!(Auto 웬만하면 사용X)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 500)
    private String password;


    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Role role;
    // 기본 생성자
    @Builder
    // 생성자
    public UserEntity(String email, String password,Role role) {
        this.email = email;
        this.password = password;
        this.provider = Provider.local;
        this.role = role;
    }

    public User toDomain() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .provider(provider)
                .role(role)
                .build();
    }
}
