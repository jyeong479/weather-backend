package com.example.iWishTheyWereAllDead.service;

import com.example.iWishTheyWereAllDead.dto.WeatherResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherApiService {

    @Value("${WEATHER_API_SERVICE_KEY}")
    private String serviceKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    // 초단기 실황 조회
    public List<WeatherResponseDto> getUltraSrtNcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        String response = restTemplate.getForObject(uri, String.class);

        List<WeatherResponseDto> result = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            if (items.isArray()) {
                for (JsonNode item : items) {
                    String category = item.path("category").asText();
                    String value = item.path("obsrValue").asText();
                    result.add(new WeatherResponseDto(category, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 초단기예보
    public List<WeatherResponseDto> getUltraSrtFcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        String response = restTemplate.getForObject(uri, String.class);

        List<WeatherResponseDto> result = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            if (items.isArray()) {
                for (JsonNode item : items) {
                    String category = item.path("category").asText();
                    String fcstDate = item.path("fcstDate").asText();
                    String fcstTime = item.path("fcstTime").asText();
                    String value = item.path("fcstValue").asText();
                    result.add(new WeatherResponseDto(category, fcstDate, fcstTime, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 단기예보
    public List<WeatherResponseDto> getVilageFcst(String baseDate, String baseTime, String nx, String ny) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 1000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .toUri();

        String response = restTemplate.getForObject(uri, String.class);

        List<WeatherResponseDto> result = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            if (items.isArray()) {
                for (JsonNode item : items) {
                    String category = item.path("category").asText();
                    String fcstDate = item.path("fcstDate").asText();
                    String fcstTime = item.path("fcstTime").asText();
                    String Value = item.path("fcstValue").asText();
                    result.add(new WeatherResponseDto(category, fcstDate, fcstTime, Value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
