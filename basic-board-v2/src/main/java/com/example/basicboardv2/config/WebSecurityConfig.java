package com.example.basicboardv2.config;

import com.example.basicboardv2.config.filter.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/static/**",
                        "/css/**",
                        "/js/**"
                ); // 정적 리소스 경로 무시
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        // 세션 사용하지 않음을 명시함
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        new AntPathRequestMatcher("/",HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/member/join",HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/member/login",HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/write",HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/detail",HttpMethod.GET.name()),
                                        //new AntPathRequestMatcher("/update",HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/update/*",HttpMethod.GET.name()),   // 강사님코드
                                        new AntPathRequestMatcher("/access-denied",HttpMethod.GET.name()),

                                        new AntPathRequestMatcher("/refresh-token", HttpMethod.POST.name()),
                                        new AntPathRequestMatcher("/join",HttpMethod.POST.name()),
                                        new AntPathRequestMatcher("/login",HttpMethod.POST.name()),
                                        new AntPathRequestMatcher("/logout",HttpMethod.POST.name()),
                                        new AntPathRequestMatcher("/api/board/file/download/*",HttpMethod.GET.name())
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .logout(AbstractHttpConfigurer::disable)
                // JWT 필터 Security쪽 필터 실행되기 전으로 등록
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
        ;
        return http.build();
    }

    // form로그인 방식이 아니고 수동이면 해줘야함
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        };
    }
}
