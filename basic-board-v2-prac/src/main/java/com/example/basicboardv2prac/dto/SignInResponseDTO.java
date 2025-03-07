package com.example.basicboardv2prac.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponseDTO {
    private boolean isLoggedIn;
    private String userId;
    private String userName;
    private String token;
}
