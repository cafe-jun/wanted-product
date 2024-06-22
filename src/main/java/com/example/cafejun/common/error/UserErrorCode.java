package com.example.cafejun.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 1404,"사용자를 찾을수 없음");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
