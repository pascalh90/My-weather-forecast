package com.pascal.weather.weatherforecast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;

public interface WeatherForecastRepository extends CrudRepository<WeatherForecastEntity, Long>{
	
	@Query("SELECT DISTINCT location FROM WeatherForecastEntity")
	public List<String> getLocationList();
	
	public List<WeatherForecastEntity> findFirst5ByLocationOrderByIdDesc(String location);
	
}
