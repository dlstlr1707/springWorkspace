package com.example.sesstionprc.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String id;
    private String password;
    private String username;
}
