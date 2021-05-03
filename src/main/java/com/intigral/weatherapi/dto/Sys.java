package com.intigral.weatherapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sys {

    private Integer type;

    private Integer id;

    private String country;

    private Long sunrise;

    private Long sunset;
}
