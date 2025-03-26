package com.example.frontservice.dto;

import lombok.Getter;

@Getter
public class RefreshTokenClientResponseDTO {
    private int status;
    private String accessToken;
    private String refreshToken;

    public RefreshTokenResponseDTO toRefreshTokenResponseDTO() {
        return RefreshTokenResponseDTO.builder()
                .status(status)
                .accessToken(accessToken)
                .build();
    }
}
