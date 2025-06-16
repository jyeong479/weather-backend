package com.example.iWishTheyWereAllDead.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherResponseDto {

    private String category;
    private String fcstDate;
    private String fcstTime;
    private String value;

    public WeatherResponseDto() {}

    public WeatherResponseDto(String category, String fcstDate, String fcstTime, String value) {
        this.category = category;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.value = value;
    }

    public WeatherResponseDto(String category, String value) {
        this.category = category;
        this.value = value;
    }


}

