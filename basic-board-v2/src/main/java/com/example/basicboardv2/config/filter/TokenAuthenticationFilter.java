package com.example.basicboardv2.config.filter;


import com.example.basicboardv2.config.jwt.TokenProvider;
import com.example.basicboardv2.model.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // accessToken 검증 로직
        
        String requestURI = request.getRequestURI();
        log.info("Request URI: " + requestURI);
        // refreshToken 검증이 넘어온다면 다음으로 넘겨버림
        // 추후 filterChain config로 설정 바꿀수있음
        if("/refresh-token".equals(requestURI)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        if (token != null && tokenProvider.validateToken(token)==1) {
            // 토큰이 유효할 경우, 인증 정보를 설정
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Member member = tokenProvider.getTokenDetails(token);
            request.setAttribute("member", member); //SecurityContextHolder에 정보가 있지만 이방법도 있다.
            
        }else if(token != null && tokenProvider.validateToken(token)==2){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            // bearer 를 제외한 순수 jwt토큰만 반환함
            return bearerToken.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
