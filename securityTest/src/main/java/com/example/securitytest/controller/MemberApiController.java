package com.example.securitytest.controller;

import com.example.securitytest.dto.SignUpRequestDTO;
import com.example.securitytest.dto.SignUpResponseDTO;
import com.example.securitytest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public SignUpResponseDTO join(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        System.out.println("SignUpRequestDTO :: " + signUpRequestDTO);
        memberService.signUp(signUpRequestDTO.toMember(bCryptPasswordEncoder));

        return SignUpResponseDTO.builder().build();
    }

}
