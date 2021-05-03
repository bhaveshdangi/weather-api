package com.intigral.weatherapi.dto;

import com.intigral.weatherapi.controller.validator.NotNullField;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@NotNullField.List({
        @NotNullField(fieldName = "city", condition = "lat == null && lon == null && country == null", message = "City must not be null or empty."),
        @NotNullField(fieldName = "country", condition = "lat == null && lon == null && city = null", message = "Country must not be null or empty."),
        @NotNullField(fieldName = "lat", condition = "city == null && country == null", message = "Latitude must not be null or empty."),
        @NotNullField(fieldName = "lon", condition = "city == null && country == null", message = "Longitude must not be null or empty."),
})
public class WeatherRequest {

    private String city;

    private String country;

    private String lat;

    private String lon;

}
