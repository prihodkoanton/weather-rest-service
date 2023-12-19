package com.andersen.weatherrestservice.service;

import com.andersen.weatherrestservice.model.WeatherEntity;

import java.util.List;

public interface WeatherService {
    WeatherEntity createWeatherRecord(WeatherEntity weatherData);

    List<WeatherEntity> getWeather(String date, List<String> cities, String sort);

    WeatherEntity getWeatherById(Long id);
}
