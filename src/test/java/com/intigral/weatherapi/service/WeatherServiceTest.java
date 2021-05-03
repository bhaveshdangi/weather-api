package com.intigral.weatherapi.service;

import com.intigral.weatherapi.dto.WeatherRequest;
import com.intigral.weatherapi.dto.WeatherResponse;
import com.intigral.weatherapi.feign.client.WeatherFeignClient;
import feign.FeignException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherFeignClient weatherFeignClient;

    @Value("${weather.api.url}")
    private String weatherurl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Test
    public void shouldReturnWeather() {

        WeatherRequest request = WeatherRequest.builder()
                .city("Dubai")
                .build();

        WeatherResponse response = WeatherResponse.builder()
                .name(request.getCity())
                .id(292223L)
                .build();

        Mockito.when(weatherFeignClient.getWeather(isA(Map.class), eq(weatherApiKey))).thenReturn(response);

        WeatherResponse expected = weatherService.getWeather(request);

        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getName(), response.getName());
    }

    @Test(expected = FeignException.FeignClientException.class)
    public void shouldReturnException() {

        WeatherRequest request = WeatherRequest.builder()
                .city("Dubai")
                .build();

        Mockito.when(weatherFeignClient.getWeather(isA(Map.class), eq(weatherApiKey))).thenThrow(FeignException.FeignClientException.class);

        weatherService.getWeather(request);

    }

}
