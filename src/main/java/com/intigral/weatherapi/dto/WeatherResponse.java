package com.intigral.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherResponse {

    private Coordinates coord;

    private List<Weather> weather;

    private String base;

    private Main main;

    private Long visibility;

    private Wind wind;

    private Cloud clouds;

    private Long dt;

    private Sys sys;

    private Long timezone;

    private Long id;

    private String name;

    private Integer cod;

}
