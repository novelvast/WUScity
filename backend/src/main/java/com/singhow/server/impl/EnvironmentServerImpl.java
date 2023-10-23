package com.singhow.server.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.singhow.POJO.Environment;
import com.singhow.POJO.Geo;
import com.singhow.common.ApiRequester;
import com.singhow.common.Result;
import com.singhow.server.EnvironmentServer;
import com.singhow.server.GeoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EnvironmentServerImpl implements EnvironmentServer {

    static String baseURL = "https://devapi.qweather.com/v7/air/now";
    static String authKey = "264fd4af6c454790ae9734261ce84943";

    @Autowired
    ApiRequester apiRequester;

    @Autowired
    GeoServer geoServer;

    Environment environment = new Environment();

    @Override
    public Result getEnvironment(String city) {

        Geo geo = (Geo) geoServer.getGeo(city).getData();

        // 接收参数为经纬度
        JsonNode jsonNode = apiRequester.request(baseURL+"?location="+geo.getLocation()+"&key="+authKey);
        //
        //
        // System.out.println(baseURL+"?location="+geo.getLocation()+"&key="+authKey);

        // 提取数据给environment
        environment.setAqi(jsonNode.get("now").get("aqi").asText());
        environment.setLevel(jsonNode.get("now").get("level").asText());
        environment.setCategory(jsonNode.get("now").get("category").asText());
        environment.setPrimary(jsonNode.get("now").get("primary").asText());
        environment.setPm10(jsonNode.get("now").get("pm10").asText());
        environment.setPm2p5(jsonNode.get("now").get("pm2p5").asText());
        environment.setNo2(jsonNode.get("now").get("no2").asText());
        environment.setSo2(jsonNode.get("now").get("so2").asText());
        environment.setCo(jsonNode.get("now").get("co").asText());
        environment.setO3(jsonNode.get("now").get("o3").asText());



        return Result.success(environment);
    }
}
