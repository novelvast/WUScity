package com.singhow.server.impl;

import com.singhow.POJO.Geo;
import com.singhow.POJO.OVOData;
import com.singhow.common.Result;
import com.singhow.server.GeoServer;
import com.singhow.server.OVOServer;
import com.singhow.utils.OVOPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

@Service
public class ovoServerImpl implements OVOServer {

    @Autowired
    GeoServer geoServer;

    static String URL="https://www.ovo.com.cn/city/";

    @Override
    public Result getAll(String city) {

        Geo geo = (Geo) geoServer.getGeo(city).getData();
        OVOPageProcessor ovoPageProcessor = new OVOPageProcessor();


        // 接收参数为特殊adcode(直辖市不同)
        System.out.println("开始爬取ovo页面" + geo.getAdcode());
        Spider.create(ovoPageProcessor).addUrl(URL + geo.getAdcode()).run();
        OVOData ovoData = ovoPageProcessor.getOVODate();
        return Result.success(ovoData);
    }
}
