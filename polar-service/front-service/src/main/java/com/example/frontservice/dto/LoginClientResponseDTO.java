package com.example.frontservice.dto;

import lombok.Getter;

@Getter
public class LoginClientResponseDTO {
    private boolean loggedIn;
    private String userName;
    private String userId;
    private String accessToken;
    private String refreshToken;
}
