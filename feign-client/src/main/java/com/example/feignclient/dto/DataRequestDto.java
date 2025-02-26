package com.example.feignclient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataRequestDto {
    private String name;
    private int value;
}
