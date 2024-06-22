package com.example.cafejun.interceptor;

import com.example.cafejun.common.error.ErrorCode;
import com.example.cafejun.common.error.TokenErrorCode;
import com.example.cafejun.common.exception.ApiException;
import com.example.cafejun.service.auth.CustomTokenProviderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
@Component //
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final CustomTokenProviderService customTokenProviderService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Authorization Interceptor url : {}", request.getRequestURI());
        log.info("Authorization Interceptor header : {}", request.getHeader("Authorization"));

        // TODO header 검증
        String accessToken = request.getHeader("Authorization").replace("Bearer ","");

        if(accessToken == null) {
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }
        Map map = customTokenProviderService.validateToken(accessToken);
        var parseUserId = map.get("userId");
        Objects.requireNonNull(parseUserId, () -> { throw new ApiException(ErrorCode.NULL_POINT);});
        Long userId = Long.parseLong(parseUserId.toString());
        if(userId != null) {
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId",userId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }
        throw new ApiException(ErrorCode.SERVER_ERROR,"인증 실패");
    }
}
