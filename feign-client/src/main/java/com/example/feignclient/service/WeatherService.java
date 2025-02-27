package com.example.feignclient.service;

import com.example.feignclient.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.api.key}")
    private String accessKey;

    private final WeatherClient weatherClient;

    // Get 호출
    public String getWeather(int pageNo,int numOfNum,String dataType,
                             String base_date,String base_time,int nx,int ny
    ) {
        return weatherClient.getWeather(pageNo,numOfNum,dataType,base_date,base_time,nx,ny,accessKey);
    }
}
