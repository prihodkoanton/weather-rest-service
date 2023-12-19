package com.andersen.weatherrestservice.repository;

import com.andersen.weatherrestservice.model.WeatherEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherEntityRepository extends JpaRepository<WeatherEntity, Long> {

//    List<WeatherEntity> findByDateAndCity(LocalDate date, String city, String sort);

//    @Query("SELECT w FROM WeatherEntity w ORDER BY w.date ASC")
//    List<WeatherEntity> findAllOrderByDateAsc();
//
//    @Query("SELECT w FROM WeatherEntity w ORDER BY w.date DESC")
//    List<WeatherEntity> findAllOrderByDateDesc();

    List<WeatherEntity> findByDateAndCityIn(LocalDate date, List<String> cities, Sort sort);

    List<WeatherEntity> findByDate(LocalDate date, Sort sort);

    List<WeatherEntity> findByCityIn(List<String> cities, Sort sort);

    List<WeatherEntity> findAll(Sort sort);
}
