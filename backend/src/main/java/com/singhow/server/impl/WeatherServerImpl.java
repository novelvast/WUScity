package com.singhow.server.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.singhow.POJO.Weather;
import com.singhow.common.ApiRequester;
import com.singhow.common.Result;
import com.singhow.server.WeatherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WeatherServerImpl implements WeatherServer {
    static String baseURL="https://api.oioweb.cn/api/weather/weather";

    @Autowired
    ApiRequester apiRequester;

    Weather weather = new Weather();


    @Override
    public Result getWeather(String city) {
        // 接收参数为城市名，可直接使用

        JsonNode jsonNode = apiRequester.request(baseURL+"?city_name="+city);
        // 提取数据给weather
        weather.setCurrent_condition(jsonNode.get("result").get("current_condition").asText());
        weather.setCurrent_temperature(jsonNode.get("result").get("current_temperature").asText());
        weather.setDat_high_temperature(jsonNode.get("result").get("dat_high_temperature").asText());
        weather.setDat_low_temperature(jsonNode.get("result").get("dat_low_temperature").asText());
        weather.setDat_weather_icon_id(jsonNode.get("result").get("dat_weather_icon_id").asText());

        return Result.success(weather);
    }
}
