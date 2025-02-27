package com.example.essentials.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
