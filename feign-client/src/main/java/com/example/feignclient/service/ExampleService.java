package com.example.feignclient.service;

import com.example.feignclient.client.ExampleClient;
import com.example.feignclient.dto.DataRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    // Get 요청 호출
    public String getData(Long id) {
        return exampleClient.getData(id);
    }

    // Post
    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequestDto.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    // Put
    public String updateDate(Long id, String name, int value) {
        String foundID = exampleClient.getData(id);
        try {
            JSONObject jsonObj = new JSONObject(foundID);
            return exampleClient.updateData(Long.parseLong(jsonObj.get("id").toString()),
                    DataRequestDto.builder()
                            .name(name)
                            .value(value)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    // Delete
    public String deleteData(Long id) {
        String foundID = exampleClient.getData(id);
        try {
            JSONObject jsonObj = new JSONObject(foundID);
            return exampleClient.deleteData(Long.parseLong(jsonObj.get("id").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    // Get 전체조회
    public String getAllData() {
        return exampleClient.getAllData();
    }
}
