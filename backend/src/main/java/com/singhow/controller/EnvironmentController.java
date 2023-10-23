package com.singhow.controller;

import com.singhow.common.Result;
import com.singhow.server.EnvironmentServer;
import com.singhow.server.WeatherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

    @Autowired
    EnvironmentServer environmentServer;

    @GetMapping("/{city}")
    public Result getEnvironment(@PathVariable("city")String city){
        return environmentServer.getEnvironment(city);
    }


}
