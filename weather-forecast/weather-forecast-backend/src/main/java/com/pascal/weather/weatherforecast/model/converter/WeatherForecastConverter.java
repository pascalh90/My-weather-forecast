package com.pascal.weather.weatherforecast.model.converter;

import java.util.List;
import java.util.Map;

import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;
import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastResponse;

public interface WeatherForecastConverter {

	public List<WeatherForecastEntity> toWeatherForecastEntityList(
			Map<LocationResponse, WeatherForecastResponse> weatherForecastMap);

}