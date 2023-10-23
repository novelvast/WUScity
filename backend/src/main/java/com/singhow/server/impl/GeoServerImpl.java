package com.singhow.server.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.singhow.POJO.Geo;
import com.singhow.common.ApiRequester;
import com.singhow.common.Result;
import com.singhow.server.GeoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class GeoServerImpl implements GeoServer {
    static String[] cityList = { "上海", "上海市", "北京", "北京市", "天津", "天津市", "重庆", "重庆市" };

    static String baseURL="https://restapi.amap.com/v3/geocode/geo";

    static String authKey = "e747f3c600c2c337396ba959f526d994";

    @Autowired
    ApiRequester apiRequester;

    Geo geo = new Geo();


    @Override
    public Result getGeo(String city) {
        // 接收参数为城市名，可直接使用

        JsonNode jsonNode = apiRequester.request(baseURL+"?key="+authKey+"&address="+city);
        // 提取数据给weather
        geo.setAdcode(jsonNode.get("geocodes").get(0).get("adcode").asText());
        geo.setLocation(jsonNode.get("geocodes").get(0).get("location").asText());
        geo.setProvince(jsonNode.get("geocodes").get(0).get("province").asText());

        // 直辖市adcode编码做特殊处理，适应ovo数据库
        if(Arrays.asList(cityList).contains(city)) {
            String adcode = geo.getAdcode();
            StringBuilder sb = new StringBuilder(adcode);
            sb.replace(3, 4, String.valueOf('1'));
            geo.setAdcode(sb.toString());
        }

        return Result.success(geo);
    }
}
