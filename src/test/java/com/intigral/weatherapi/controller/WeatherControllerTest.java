package com.intigral.weatherapi.controller;

import com.intigral.weatherapi.dto.Weather;
import com.intigral.weatherapi.dto.WeatherRequest;
import com.intigral.weatherapi.dto.WeatherResponse;
import com.intigral.weatherapi.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    private MockMvc mockMvc;

    private final WeatherController weatherController;

    private final WeatherService weatherService;

    public WeatherControllerTest() {

        weatherService = Mockito.mock(WeatherService.class);
        weatherController = new WeatherController(weatherService);
    }

    @Before
    public void setUp() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(get("/weather"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void shouldReturnWeatherSuccessful() throws Exception {

        WeatherRequest request = WeatherRequest.builder()
                .city("Dubai")
                .build();

        Weather weather = Weather.builder()
                .id(800L)
                .main("Clear")
                .description("clear sky")
                .build();

        WeatherResponse response = WeatherResponse.builder()
                .name(request.getCity())
                .id(292223L)
                .weather(Collections.singletonList(weather))
                .build();

        when(weatherService.getWeather(request)).thenReturn(response);

        this.mockMvc.perform(get("/weather?city=" + request.getCity())
                .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(request.getCity()))
                .andExpect(jsonPath("$.weather[0].id").value(weather.getId()))
                .andExpect(jsonPath("$.weather[0].main").value(weather.getMain()))
                .andExpect(jsonPath("$.weather[0].description").value(weather.getDescription()));
    }
}
