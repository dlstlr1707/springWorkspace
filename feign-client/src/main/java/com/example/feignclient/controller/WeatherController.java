package com.example.feignclient.controller;

import com.example.feignclient.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam int pageNo,
                             @RequestParam int numOfNum,
                             @RequestParam String dataType,
                             @RequestParam String base_date,
                             @RequestParam String base_time,
                             @RequestParam int nx,
                             @RequestParam int ny
    ) {
        return weatherService.getWeather(pageNo, numOfNum, dataType, base_date, base_time, nx, ny);
    }
}
