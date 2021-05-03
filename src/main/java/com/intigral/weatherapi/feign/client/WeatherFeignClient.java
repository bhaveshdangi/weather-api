package com.intigral.weatherapi.feign.client;

import com.intigral.weatherapi.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "weather-api-service", url = "${weather.api.url}")
public interface WeatherFeignClient {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public WeatherResponse getWeather(@SpringQueryMap Map<String, List<Object>> queryParameters, @RequestParam(name = "appid") String appid);
}
