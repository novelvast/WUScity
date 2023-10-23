package com.singhow.server;

import com.singhow.common.Result;

public interface NewsServer {
    Result getNews(String city);
}
