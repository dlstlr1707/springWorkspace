package com.example.feignclient.controller;

import com.example.feignclient.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class ExampleApiController {

    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public String getDate(@PathVariable Long id) {
        return exampleService.getDataById(id);
    }

    @PostMapping
    public String createData(@RequestParam String name,@RequestParam int value){
        return exampleService.createData(name,value);
    }
}
