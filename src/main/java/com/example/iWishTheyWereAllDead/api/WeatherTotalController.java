package com.example.iWishTheyWereAllDead.api;

import com.example.iWishTheyWereAllDead.dto.WeatherResponseDto;
import com.example.iWishTheyWereAllDead.service.WeatherTotalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WeatherTotalController {

    private final WeatherTotalService weatherTotalService;

    public WeatherTotalController(WeatherTotalService weatherTotalService) {
        this.weatherTotalService = weatherTotalService;
    }

    @GetMapping("/weather/by-city/ncst")
    public ResponseEntity<?> getUltraSrtNcstByCity(@RequestParam String city) {
        try {
            List<WeatherResponseDto> result = weatherTotalService.getUltraSrtNcstByCity(city);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 요청 중 오류 발생");
        }
    }


    @GetMapping("/weather/by-city/ultra-forecast")
    public ResponseEntity<?> getUltraSrtFcstByCity(@RequestParam String city) {
        try {
            List<WeatherResponseDto> result = weatherTotalService.getUltraSrtFcstByCity(city);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 요청 중 오류 발생");
        }
    }

    @GetMapping("/weather/by-city/forecast")
    public ResponseEntity<?> getVilageFcstByCity(@RequestParam String city) {
        try {
            List<WeatherResponseDto> result = weatherTotalService.getVilageFcstByCity(city);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 요청 중 오류 발생");
        }
    }
}
