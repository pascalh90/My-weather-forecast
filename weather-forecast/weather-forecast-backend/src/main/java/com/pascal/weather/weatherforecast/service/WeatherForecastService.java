package com.pascal.weather.weatherforecast.service;

import java.util.List;

import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastsByLocationResponse;

public interface WeatherForecastService {

	public void fetchWeather();

	public List<WeatherForecastsByLocationResponse> getRecentWeatherForecastsPerLocation();

	public List<LocationResponse> getLocations();
}