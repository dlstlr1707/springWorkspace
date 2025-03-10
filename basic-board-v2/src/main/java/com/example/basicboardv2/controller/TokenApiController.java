package com.example.basicboardv2.controller;

import com.example.basicboardv2.config.jwt.TokenProvider;
import com.example.basicboardv2.dto.RefreshTokenResponseDTO;
import com.example.basicboardv2.dto.SignInResponseDTO;
import com.example.basicboardv2.model.Member;
import com.example.basicboardv2.service.TokenApiService;
import com.example.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenApiService tokenApiService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        RefreshTokenResponseDTO result = tokenApiService.refreshToken(request, response);

        if(result.isValidate()){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh Token이 유효하지 않습니다.");
        }
    }
}
