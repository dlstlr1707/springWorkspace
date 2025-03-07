package com.example.basicboardv2prac.dto;

import com.example.basicboardv2prac.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDTO {
    private Long id;
    private String userName;
    private String userId;
    private Role role;
}
