package com.example.basicboardv2.service;

import com.example.basicboardv2.dto.SignUpRequestDTO;
import com.example.basicboardv2.dto.SignUpResponseDTO;
import com.example.basicboardv2.mapper.MemberMapper;
import com.example.basicboardv2.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void signUp(Member member) {
        memberMapper.saved(member);
    }

}
