package com.andersen.weatherrestservice.controller;

import com.andersen.weatherrestservice.model.WeatherEntity;
import com.andersen.weatherrestservice.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    private final String editorToken;
    private final WeatherService weatherService;

    public WeatherController(@Value("${token.editor-role}") String editorToken,
                             WeatherService weatherService) {
        this.editorToken = editorToken;
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<WeatherEntity> createWeatherRecord(@RequestBody WeatherEntity weatherData,
                                                             @RequestHeader(name = "authToken", required = false) String headerToken) {
        if (editorToken.equals(headerToken)) {
            WeatherEntity createdWeatherData = weatherService.createWeatherRecord(weatherData);
            return new ResponseEntity<>(createdWeatherData, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping
    public ResponseEntity<List<WeatherEntity>> getWeather(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) List<String> city,
            @RequestParam(required = false) String sort) {
        List<WeatherEntity> weather = weatherService.getWeather(date, city, sort);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherEntity> getWeatherRecordById(@PathVariable Long id) {
        WeatherEntity weather = weatherService.getWeatherById(id);
        if (weather == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }
}
