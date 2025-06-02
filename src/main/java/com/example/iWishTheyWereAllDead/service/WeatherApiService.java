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
    public String getUltraSrtNcst() throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey +
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") +
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8") +
                "&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8") +
                "&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode("20250602", "UTF-8") +
                "&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8") +
                "&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode("55", "UTF-8") +
                "&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode("127", "UTF-8");

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
    public String getUltraSrtFcst() throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst" + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey +
                "&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") +
                "&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8") +
                "&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8") + /*요청자료형식(XML/JSON) Default: XML*/
                "&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20250602", "UTF-8") + /*‘21년 6월 28일 발표*/
                "&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0930", "UTF-8") + /*06시30분 발표(30분 단위)*/
                "&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8") + /*예보지점 X 좌표값*/
                "&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8"); /*예보지점 Y 좌표값*/

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
    public String getVilageFcst() throws IOException {
        String urlBuilder = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst" + "?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey + /*Service Key*/
                "&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") +  /*페이지번호*/
                "&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8") + /*한 페이지 결과 수*/
                "&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8") + /*요청자료형식(XML/JSON) Default: XML*/
                "&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20250602", "UTF-8") + /*‘21년 6월 28일발표*/
                "&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8") + /*05시 발표*/
                "&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8") + /*예보지점의 X 좌표값*/
                "&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8"); /*예보지점의 Y 좌표값*/

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
