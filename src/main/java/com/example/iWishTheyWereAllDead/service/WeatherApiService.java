package com.example.iWishTheyWereAllDead.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class WeatherApiService {

    @Value("${WEATHER_API_SERVICE_KEY}")
    private String serviceKey;

    // 초단기 실황 조회
    public String getUltraSrtNcst(String baseDate, String baseTime, String nx, String ny) throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" + "?" +
                URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey +
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=1" +
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=1000" +
                "&" + URLEncoder.encode("dataType", "UTF-8") + "=JSON" +
                "&" + URLEncoder.encode("base_date", "UTF-8") + "=" + baseDate +
                "&" + URLEncoder.encode("base_time", "UTF-8") + "=" + baseTime +
                "&" + URLEncoder.encode("nx", "UTF-8") + "=" + nx +
                "&" + URLEncoder.encode("ny", "UTF-8") + "=" + ny;

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    // 초단기예보
    public String getUltraSrtFcst(String baseDate, String baseTime, String nx, String ny) throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst" + "?" +
                URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey +
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=1" +
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=1000" +
                "&" + URLEncoder.encode("dataType", "UTF-8") + "=JSON" +
                "&" + URLEncoder.encode("base_date", "UTF-8") + "=" + baseDate +
                "&" + URLEncoder.encode("base_time", "UTF-8") + "=" + baseTime +
                "&" + URLEncoder.encode("nx", "UTF-8") + "=" + nx +
                "&" + URLEncoder.encode("ny", "UTF-8") + "=" + ny;

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    // 단기예보
    public String getVilageFcst(String baseDate, String baseTime, String nx, String ny) throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst" + "?" +
                URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey +
                "&" + URLEncoder.encode("pageNo","UTF-8") + "=1" +
                "&" + URLEncoder.encode("numOfRows","UTF-8") + "=1000" +
                "&" + URLEncoder.encode("dataType","UTF-8") + "=JSON" +
                "&" + URLEncoder.encode("base_date","UTF-8") + "=" + baseDate +
                "&" + URLEncoder.encode("base_time","UTF-8") + "=" + baseTime +
                "&" + URLEncoder.encode("nx","UTF-8") + "=" + nx +
                "&" + URLEncoder.encode("ny","UTF-8") + "=" + ny;

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }
}
