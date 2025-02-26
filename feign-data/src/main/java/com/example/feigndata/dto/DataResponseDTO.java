package com.example.feigndata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class DataResponseDTO {
    private Long id;
    private String name;
    private int value;
}
