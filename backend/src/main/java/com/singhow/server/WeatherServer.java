package com.singhow.server;

import com.singhow.common.Result;

public interface WeatherServer {
    Result getWeather(String city);
}
