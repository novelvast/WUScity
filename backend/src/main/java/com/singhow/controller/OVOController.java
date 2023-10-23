package com.singhow.controller;

import com.singhow.common.Result;
import com.singhow.server.OVOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ovo")
public class OVOController {

    @Autowired
    OVOServer ovoServer;

    @GetMapping("/{city}")
    public Result getOVOdata(@PathVariable("city")String city){
        return ovoServer.getAll(city);
    }


}
