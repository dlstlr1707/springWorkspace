package com.example.securitytest.service;

import com.example.securitytest.confing.security.CustomUserDetails;
import com.example.securitytest.mapper.MemberMapper;
import com.example.securitytest.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final MemberMapper memberMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.selectMemberByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return CustomUserDetails.builder()
                .member(member)
                .build();
    }
}
