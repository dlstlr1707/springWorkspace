package com.example.basicboardv2prac.dto;

import com.example.basicboardv2prac.model.Member;
import com.example.basicboardv2prac.type.Role;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private Role role;

    public Member toMember(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .role(role)
                .build();
    }
}
