package com.example.authservice.dto;

import com.example.authservice.model.User;
import com.example.authservice.type.Role;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class UserJoinRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private Role role;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .userName(userName)
                .role(role)
                .build();
    }
}
