package com.example.iWishTheyWereAllDead.api;


import com.example.iWishTheyWereAllDead.service.WeatherApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherApiController {
    private final WeatherApiService weatherApiService;

    public WeatherApiController(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @GetMapping("/weather")
    public String getWeather() {
        try {
            return weatherApiService.getUltraSrtNcst();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/weather/ultra-forecast")
    public String getUltraForecast() {
        try {
            return weatherApiService.getUltraSrtFcst();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/wether/forcast")
    public String getForcast() {
        try {
            return weatherApiService.getVilageFcst();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
