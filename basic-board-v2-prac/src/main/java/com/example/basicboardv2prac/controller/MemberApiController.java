package com.example.basicboardv2prac.controller;

import com.example.basicboardv2prac.config.jwt.TokenProvider;
import com.example.basicboardv2prac.config.security.CustomUserDetails;
import com.example.basicboardv2prac.dto.SignInRequestDTO;
import com.example.basicboardv2prac.dto.SignInResponseDTO;
import com.example.basicboardv2prac.dto.SignUpRequestDTO;
import com.example.basicboardv2prac.dto.SignUpResponseDTO;
import com.example.basicboardv2prac.model.Member;
import com.example.basicboardv2prac.service.MemberService;
import com.example.basicboardv2prac.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public SignUpResponseDTO signup(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signup(signUpRequestDTO.toMember(bCryptPasswordEncoder));
        return SignUpResponseDTO.builder()
                .successed(true)
                .build();
    }

    @PostMapping("/login")
    public SignInResponseDTO signIn(
            @RequestBody SignInRequestDTO signInRequestDTO,
            HttpServletResponse response

    ) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getUsername(),
                        signInRequestDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Member member = ((CustomUserDetails) authenticate.getPrincipal()).getMember();
        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));
        CookieUtil.addCookie(response,"refreshToken",refreshToken,7*24*60*60);
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
}
