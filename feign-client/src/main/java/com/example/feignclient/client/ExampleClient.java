package com.example.feignclient.client;

import com.example.feignclient.dto.DataRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="examleClient", url="${feign-data.url}")
public interface ExampleClient {

    //Get 요청 (데이터 조회)
    @GetMapping("/api/data/{id}")
    String getData(@PathVariable("id") Long id);

    // Get 요청 (데이터 전체 조회)
    @GetMapping("/api/data")
    String getAllData();

    @PostMapping("/api/data")
    String createData(@RequestBody DataRequestDto dto);

    // 과제 update 부분
    @PutMapping("/api/data/{id}")
    String updateData(@PathVariable("id") Long id, @RequestBody DataRequestDto dto);

    // 과제 delete 부분
    @DeleteMapping("/api/data/{id}")
    String deleteData(@PathVariable("id") Long id);
}
