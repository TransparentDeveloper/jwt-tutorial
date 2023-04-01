package com.example.jwttutorial.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.SecurityFilterChain;


/**
 * SecurityFilterChain: 특정한 요청 매핑(예를 들어 URL 패턴이나 HTTP 메서드)에 대해 적용되는 보안 필터 목록
 * WebSecurityCustomizer: 보안 구성을 정의
 */
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
public class SecurityConfig {
    /*
    WebSecurityConfigurerAdapter deprecate 에러발생

    원인: 스프링 버전이 업데이트 됨에 따라 오버라이딩 대신 설정을 Bean 으로 등록 하도록 정책이 바꿔었다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {

        /*
        authorizeRequests deprecate 에러발생

        해결:
            .authorizeRequests() → .authorizeHttpRequests()
            .antMatchers() → .requestMatchers()
         */
        http
                .authorizeHttpRequests()    // HttpServlet Request 를 사용하는 요청에 대한 접근제한 설정
                .requestMatchers("/api/hello").permitAll() // "/api/hello" 에 대한 요청은 인증없이 접근 허용
                .anyRequest().authenticated(); // 나머지 요청은 모두 인증

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //h2 하위의 모든 요청들과 파비콘 관련 요청은 무시한다.
        return (web) -> web.ignoring().requestMatchers("/h2-console/**", "/favicon.ico");
    }


}
