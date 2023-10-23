package com.singhow.controller;

import com.singhow.common.Result;
import com.singhow.server.OVOServer;
import com.singhow.server.WeatherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherServer weatherServer;

    @GetMapping("/{city}")
    public Result getWeather(@PathVariable("city")String city){
        return weatherServer.getWeather(city);
    }


}
