package com.example.orderservice.order.web;

import com.example.orderservice.order.domain.Order;
import com.example.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Mono<Order> submitOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.submitOrder(
                orderRequestDTO.isbn(), orderRequestDTO.quantity()
        );
    }
}
