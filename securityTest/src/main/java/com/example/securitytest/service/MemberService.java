package com.example.securitytest.service;

import com.example.securitytest.mapper.MemberMapper;
import com.example.securitytest.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void signUp(Member member) {
        memberMapper.insertMember(member);
    }

    public void signIn(Member member) {
    }
}
