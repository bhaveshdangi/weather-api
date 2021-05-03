package com.intigral.weatherapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Main {

    private Double temp;

    private Double feels_like;

    private Double temp_min;

    private Double temp_max;

    private Integer pressure;

    private Integer humidity;
}
