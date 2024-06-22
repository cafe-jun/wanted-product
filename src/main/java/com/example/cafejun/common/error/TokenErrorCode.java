package com.example.cafejun.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Token 의 경우 2000번대 에러코드 사용
 */

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs {

    INVALID_TOKEN(400 , 2000 , "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 2002, "토큰 알수없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰 없음"),
    UNSUPPORT_TOKEN(400, 2003, "지원되지 않는 토큰 에러"),

    ;
    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
