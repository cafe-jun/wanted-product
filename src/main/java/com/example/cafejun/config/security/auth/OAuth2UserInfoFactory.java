//package com.example.cafejun.config.security.auth;
//
//import com.cafejun.fuspring.advise.assertThat.DefaultAssert;
//import com.example.cafejun.config.security.auth.provider.Google;
//import com.example.cafejun.config.security.auth.provider.Kakao;
//import com.example.cafejun.config.security.auth.provider.Naver;
//import com.cafejun.fuspring.domain.entity.member.Provider;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Map;
//
//@Slf4j
//public class OAuth2UserInfoFactory {
//    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
//        log.info("provider response attribute");
//        for(String keys : attributes.keySet()) {
//            log.info("{}",attributes.get(keys));
//        }
//        if(registrationId.equalsIgnoreCase(Provider.google.toString())) {
//            return new Google(attributes);
//        } else if (registrationId.equalsIgnoreCase(Provider.naver.toString())) {
//            return new Naver(attributes);
//        } else if (registrationId.equalsIgnoreCase(Provider.kakao.toString())) {
//            return new Kakao(attributes);
//        } else {
//            DefaultAssert.isAuthentication("해당 oauth2 기능은 지원하지 않습니다.");
//        }
//        return null;
//    }
//}
