package com.example.feignclient.controller;

import com.example.feignclient.dto.DataResponseDTO;
import com.example.feignclient.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class ExampleApiController {

    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public String getData(@PathVariable Long id) {
        return exampleService.getData(id);
    }

    @PostMapping
    public String createData(@RequestParam String name,@RequestParam int value){
        return exampleService.createData(name,value);
    }
    /*
    // 숙제 코드 결과
    @PutMapping("/{id}")
    public String updateData(@PathVariable Long id, @RequestParam String name,@RequestParam int value){
        return exampleService.updateDate(id,name,value);
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        return exampleService.deleteData(id);
    }

    @GetMapping
    public String getAllData() {
        return exampleService.getAllData();
    }
    */
    // 강사님 코드
    @PutMapping("{id}")
    public String updateData(@PathVariable Long id,@RequestParam String name,@RequestParam int value){
        return exampleService.updateData(id,name,value);
    }
    @GetMapping("/all")
    public List<DataResponseDTO> getAll() {
        return exampleService.getAll();
    }
    @DeleteMapping("{id}")
    public String deleteData(@PathVariable Long id) {
        return exampleService.deleteData(id);
    }
}
