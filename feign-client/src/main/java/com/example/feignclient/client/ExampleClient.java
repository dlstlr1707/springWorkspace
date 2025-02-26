package com.example.feignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="examleClient", url="${feign-data.url}")
public interface ExampleClient {

    //Get 요청 (데이터 조회)
    @GetMapping("/api/data/{id}")
    String getData(@PathVariable("id") Long id);
}
