package com.example.frontservice.client;

import com.example.frontservice.dto.CreateOrderRequestDTO;
import com.example.frontservice.dto.CreateOrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "orderClient", url = "${polar.edge-service-url}/orders")
public interface OrderClient {
    @PostMapping
    CreateOrderResponseDTO createOrder(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateOrderRequestDTO createOrderRequestDTO
    );
}
