package com.example.cookieprc.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userid;
    private String password;
    private String username;
}
