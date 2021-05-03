package com.intigral.weatherapi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private Long id;

    private String main;

    private String description;

    private String icon;
}
