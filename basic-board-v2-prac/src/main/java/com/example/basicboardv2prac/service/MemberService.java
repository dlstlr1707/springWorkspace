package com.example.basicboardv2prac.service;

import com.example.basicboardv2prac.dto.SignUpRequestDTO;
import com.example.basicboardv2prac.dto.SignUpResponseDTO;
import com.example.basicboardv2prac.mapper.MemberMapper;
import com.example.basicboardv2prac.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void signup(Member member) {
        memberMapper.saved(member);
    }
}
