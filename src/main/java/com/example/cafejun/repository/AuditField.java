package com.example.cafejun.repository;

import com.example.cafejun.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public class AuditField extends DefaultTime {


    @CreatedBy
    protected String createdBy; // 생성자

    @LastModifiedBy
    protected String modifiedBy; // 수정자
}
