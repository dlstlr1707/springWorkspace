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
    public String getData(@PathVariable Long id) {
        return exampleService.getData(id);
    }

    @GetMapping
    public String getAllData() {
        return exampleService.getAllData();
    }

    @PostMapping
    public String createData(@RequestParam String name,@RequestParam int value){
        return exampleService.createData(name,value);
    }

    @PutMapping("/{id}")
    public String updateData(@PathVariable Long id, @RequestParam String name,@RequestParam int value){
        return exampleService.updateDate(id,name,value);
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        return exampleService.deleteData(id);
    }
}
