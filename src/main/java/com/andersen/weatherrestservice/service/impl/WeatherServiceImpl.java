package com.andersen.weatherrestservice.service.impl;

import com.andersen.weatherrestservice.model.WeatherEntity;
import com.andersen.weatherrestservice.repository.WeatherEntityRepository;
import com.andersen.weatherrestservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final WeatherEntityRepository weatherEntityRepository;

    @Override
    public WeatherEntity createWeatherRecord(WeatherEntity weatherEntity) {
        return weatherEntityRepository.save(weatherEntity);
    }

    @Override
    public List<WeatherEntity> getWeather(String date, List<String> cities, String sort) {
        Sort sortOrder = Sort.by("date").ascending();
        if (sort != null && sort.equals("-date")) {
            sortOrder = Sort.by("date").descending().and(Sort.by("id").ascending());
        }

        if (date != null && cities != null && !cities.isEmpty()) {
            return weatherEntityRepository.findByDateAndCityIn(LocalDate.parse(date), cities, sortOrder);
        } else if (date != null) {
            return weatherEntityRepository.findByDate(LocalDate.parse(date), sortOrder);
        } else if (cities != null && !cities.isEmpty()) {
            return weatherEntityRepository.findByCityIn(cities, sortOrder);
        } else {
            return weatherEntityRepository.findAll(sortOrder);
        }
    }

    @Override
    public WeatherEntity getWeatherById(Long id) {
        WeatherEntity weather = weatherEntityRepository.findById(id).orElse(null);
        if (weather != null) {
            log.info("getWeatherById {}", weather);
        }
        return weather;
    }
}
