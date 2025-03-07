package com.example.basicboardv2prac.dto;

import com.example.basicboardv2prac.model.Member;
import lombok.Getter;

@Getter
public class SignInRequestDTO {
    private String username;
    private String password;
}
