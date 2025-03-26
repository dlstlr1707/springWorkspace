package com.example.frontservice.dto;

import com.example.frontservice.type.Role;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class JoinRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private Role role;

}
