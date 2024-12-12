package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather.api.key}")
    public String APIKey;
    public final static  String API="http://api.weatherstack.com/current?access_key=APIKey&query=CITY";

    @Autowired
    public RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi=API.replace("CITY",city).replace("APIKey",APIKey);
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = responseEntity.getBody();
        return body;
    }
}
