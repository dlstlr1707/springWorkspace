package com.example.frontservice.dto;

import lombok.Getter;

@Getter
public class CreateOrderRequestDTO {
    private String isbn;
    private int quantity;
}
