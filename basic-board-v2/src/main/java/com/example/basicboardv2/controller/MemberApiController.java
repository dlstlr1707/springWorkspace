package com.example.basicboardv2.controller;

import com.example.basicboardv2.config.jwt.TokenProvider;
import com.example.basicboardv2.config.security.CustomUserDetails;
import com.example.basicboardv2.dto.*;
import com.example.basicboardv2.model.Member;
import com.example.basicboardv2.service.MemberService;
import com.example.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return SignUpResponseDTO.builder()
                .successed(true)
                .build();
    }

    @PostMapping("/login")
    public SignInResponseDTO signIn(
            @RequestBody SignInRequestDTO signInRequestDTO,
            HttpServletResponse response
    ) {
        // 사용자 인증
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getUsername(),
                        signInRequestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        // 토큰 생성에 필요해서 가져옴
        Member member = ((CustomUserDetails) authenticate.getPrincipal()).getMember();
        
        // 브라우저에 저장
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        // 서버에 저장
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));

        CookieUtil.addCookie(response,"refreshToken",refreshToken, 7*24*60*60);

        return SignInResponseDTO.builder()
                .isLoggedIn(true)
                .token(accessToken)
                .userId(member.getUserId())
                .userName(member.getUserName())
                .build();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request,response,"refreshToken");
    }

    @GetMapping("/user/info")
    public UserInfoResponseDTO getUserInfo(HttpServletRequest request) {
        Member member = (Member) request.getAttribute("member");
        return UserInfoResponseDTO.builder()
                .id(member.getId())
                .userName(member.getUserName())
                .userId(member.getUserId())
                .role(member.getRole())
                .build();
    }
}
