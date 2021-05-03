package com.intigral.weatherapi.service;

import com.intigral.weatherapi.dto.WeatherRequest;
import com.intigral.weatherapi.dto.WeatherResponse;
import com.intigral.weatherapi.feign.client.WeatherFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class WeatherService {

    private final WeatherFeignClient weatherFeignClient;

    @Value("${weather.api.url}")
    private String weatherurl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    public WeatherService(WeatherFeignClient weatherFeignClient) {
        this.weatherFeignClient = weatherFeignClient;
    }

    @Cacheable(key = "#weatherRequest", value = "weather")
    public WeatherResponse getWeather(WeatherRequest weatherRequest) {

        Map<String, List<Object>> uriVariables = new HashMap<>();

        uriVariables.put("q", Arrays.asList(weatherRequest.getCity(), weatherRequest.getCountry()));
        uriVariables.put("lat", Collections.singletonList(weatherRequest.getLat()));
        uriVariables.put("lon", Collections.singletonList(weatherRequest.getLon()));

        log.info("Calling third party weather API to get weather data.");


        return weatherFeignClient.getWeather(uriVariables, weatherApiKey);

    }
}
