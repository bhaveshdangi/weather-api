package com.intigral.weatherapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Wind {

    private Double speed;

    private Integer deg;
}
