package com.example.feignclient.service;

import com.example.feignclient.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    // Get 요청 호출
    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }
}
