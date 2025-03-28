package com.example.frontservice.service;

import com.example.frontservice.client.OrderClient;
import com.example.frontservice.dto.CreateOrderRequestDTO;
import com.example.frontservice.dto.CreateOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderClient orderClient;

    public CreateOrderResponseDTO createOrder(String accessToken, CreateOrderRequestDTO createOrderRequestDTO) {
        return orderClient.createOrder(accessToken, createOrderRequestDTO);
    }
}
