package com.example.basicboardv2.service;

import com.example.basicboardv2.config.jwt.TokenProvider;
import com.example.basicboardv2.dto.RefreshTokenResponseDTO;
import com.example.basicboardv2.model.Member;
import com.example.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenApiService {
    private final TokenProvider tokenProvider;

    public RefreshTokenResponseDTO refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtil.getCookieValue(request,"refreshToken");

        if(refreshToken != null && tokenProvider.validateToken(refreshToken) == 1) {
            Member member = tokenProvider.getTokenDetails(refreshToken);

            String newAccessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
            String newRefreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));

            CookieUtil.addCookie(response,"refreshToken",newRefreshToken,7*24*60*60);

            response.setHeader(HttpHeaders.AUTHORIZATION, newAccessToken);

            return RefreshTokenResponseDTO.builder()
                    .isValidate(true)
                    .userName(member.getUserName())
                    .userId(member.getUserId())
                    .token(newAccessToken)
                    .build();
        }else{
            return RefreshTokenResponseDTO.builder()
                    .isValidate(false)
                    .build();
        }
    }
}
