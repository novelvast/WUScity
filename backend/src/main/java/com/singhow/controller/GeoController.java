package com.singhow.controller;

import com.singhow.POJO.Geo;
import com.singhow.common.Result;
import com.singhow.server.GeoServer;
import com.singhow.server.WeatherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    GeoServer geoServer;

    @GetMapping("/{city}")
    public Result getGeo(@PathVariable("city")String city){
        return geoServer.getGeo(city);
    }


}
