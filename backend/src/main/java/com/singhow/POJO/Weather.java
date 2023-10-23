package com.singhow.POJO;

public class Weather {
    private String current_condition;

    private String current_temperature;

    private String dat_high_temperature;

    private String dat_low_temperature;

    private String dat_weather_icon_id;

    public String getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(String current_condition) {
        this.current_condition = current_condition;
    }

    public String getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(String current_temperature) {
        this.current_temperature = current_temperature;
    }

    public String getDat_high_temperature() {
        return dat_high_temperature;
    }

    public void setDat_high_temperature(String dat_high_temperature) {
        this.dat_high_temperature = dat_high_temperature;
    }

    public String getDat_low_temperature() {
        return dat_low_temperature;
    }

    public void setDat_low_temperature(String dat_low_temperature) {
        this.dat_low_temperature = dat_low_temperature;
    }

    public String getDat_weather_icon_id() {
        return dat_weather_icon_id;
    }

    public void setDat_weather_icon_id(String dat_weather_icon_id) {
        this.dat_weather_icon_id = dat_weather_icon_id;
    }


}
