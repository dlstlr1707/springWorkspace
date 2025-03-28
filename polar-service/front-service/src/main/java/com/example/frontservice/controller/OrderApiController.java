package com.example.frontservice.controller;

import com.example.frontservice.dto.CreateOrderRequestDTO;
import com.example.frontservice.dto.CreateOrderResponseDTO;
import com.example.frontservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webs/api/order")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public CreateOrderResponseDTO createOrder(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String accessToken,
            @RequestBody CreateOrderRequestDTO createOrderRequestDTO
    ){
        return orderService.createOrder(accessToken, createOrderRequestDTO);
    }
}
