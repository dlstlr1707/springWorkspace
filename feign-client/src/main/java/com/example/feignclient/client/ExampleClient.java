package com.example.feignclient.client;

import com.example.feignclient.dto.DataRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="examleClient", url="${feign-data.url}")
public interface ExampleClient {

    //Get 요청 (데이터 조회)
    @GetMapping("/api/data/{id}")
    String getData(@PathVariable("id") Long id);

    @PostMapping("/api/data")
    String createDate(@RequestBody DataRequestDto dto);
}
