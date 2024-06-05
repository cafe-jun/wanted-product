//package com.example.cafejun.config.security.handler;
//
//import com.example.cafejun.advise.assertThat.DefaultAssert;
//import com.example.cafejun.config.util.CustomCookie;
//import com.example.cafejun.domain.user.Token;
//import com.example.cafejun.domain.user.TokenMapping;
//import com.example.cafejun.repository.TokenRepository;
//import com.example.cafejun.service.auth.CustomTokenProviderService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Optional;
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final CustomTokenProviderService customTokenProviderService;
////    private final OAuth2Config oAuth2Config;
//    private final TokenRepository tokenRepository;
//    private final CustomAuthorizationRequestRepository customAuthorizationRequestRepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////        DefaultAssert.isAuthentication(!response.isCommitted());
//        String targetUrl = determineTargetUrl(request, response, authentication);
//        clearAuthenticationAttributes(request,response);
//        getRedirectStrategy().sendRedirect(request,response,targetUrl);
//    }
//    protected String determineTargetUrl(HttpServletRequest request,HttpServletResponse response,Authentication authentication) {
//        Optional<String> redirectUri = CustomCookie.getCookie(request,REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
//        DefaultAssert.isAuthentication(!(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())));
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//        TokenMapping tokenMapping = customTokenProviderService.createToken(authentication);
//        Token token =  Token.builder()
//                .email(tokenMapping.getEmail())
//                .refreshToken(tokenMapping.getRefreshToken())
//                .build();
//        tokenRepository.save(token);
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token",tokenMapping.getAccessToken())
//                .build().toUriString();
//    }
//    protected void clearAuthenticationAttributes(HttpServletRequest request,HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//        customAuthorizationRequestRepository.removeAuthorizationRequestCookies(request,response);
//    }
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//        return oAuth2Config.getoAuth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                        && authorizedURI.getPort() == clientRedirectUri.getPort()
//                    ) {
//                        return true;
//                    }
//                    return false;
//                });
//    }
//}
