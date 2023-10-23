package com.singhow.server.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.singhow.POJO.News;
import com.singhow.common.ApiRequester;
import com.singhow.common.Result;
import com.singhow.server.NewsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NewsServerImpl implements NewsServer {
    static String baseURL="https://apis.tianapi.com/social/index";

    static String authKey = "58511e2f8bca209fcf207ff8fea92efb";

    static final int numOfNews = 5;

    @Autowired
    ApiRequester apiRequester;

    List<News> list = new ArrayList<>();


    @Override
    public Result getNews(String city) {
        // 接收参数为城市名，可直接使用

        JsonNode jsonNode = apiRequester.request(baseURL+"?key="+authKey+"&word="+city);
        //System.out.println(baseURL+"?key="+authKey+"&word="+city);

        list.clear();
        JsonNode tempJsonNode;
        for (int i = 0; i < numOfNews; i++) {
            // 提取数据给news
            News news = new News();
            tempJsonNode = jsonNode.get("result").get("newslist").get(i);
            if(tempJsonNode == null)
                break;
            news.setTitle(tempJsonNode.get("title").asText());
            news.setCtime(tempJsonNode.get("ctime").asText());
            news.setUrl(tempJsonNode.get("url").asText());
            news.setSource(tempJsonNode.get("source").asText());

            // 加入列表
            list.add(news);
        }

        if(list.isEmpty())
            return Result.fail("无当地新闻");
        else
            return Result.success(list);
    }
}
