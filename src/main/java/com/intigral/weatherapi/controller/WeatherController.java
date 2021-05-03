package com.intigral.weatherapi.controller;

import com.intigral.weatherapi.dto.WeatherRequest;
import com.intigral.weatherapi.dto.WeatherResponse;
import com.intigral.weatherapi.service.WeatherService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Weather API",
                description = "REST API to get weather information"
        )
)
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {

        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    @Tag(name = "Weather API", description = "Returns weather data by city, country, latitude and longitude")
    public WeatherResponse getWeather(@Valid WeatherRequest request) {

        log.debug("Weather API called with request : {}", request);

        return weatherService.getWeather(request);
    }
}
