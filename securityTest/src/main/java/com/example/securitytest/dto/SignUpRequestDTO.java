package com.example.securitytest.dto;

import com.example.securitytest.model.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@ToString
public class SignUpRequestDTO {
    private String userId;
    private String password;
    private String userName;

    public Member toMember(BCryptPasswordEncoder passwordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .build();
    }
}
