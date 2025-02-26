package com.example.basicboardv2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DataResponseDTO {
    private Long id;
    private String name;
    private int value;
}
