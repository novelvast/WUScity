package com.singhow.controller;

import com.singhow.common.Result;
import com.singhow.server.GeoServer;
import com.singhow.server.NewsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsServer newsServer;

    @GetMapping("/{city}")
    public Result getNews(@PathVariable("city")String city){
        return newsServer.getNews(city);
    }


}
