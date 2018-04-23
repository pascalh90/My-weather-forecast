package com.pascal.weather.weatherforecast.model.response;

import java.util.List;

import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;

public class WeatherForecastsByLocationResponse {
	private String location;
	private List<WeatherForecastEntity> weatherForecasts;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<WeatherForecastEntity> getWeatherForecasts() {
		return weatherForecasts;
	}

	public void setWeatherForecasts(List<WeatherForecastEntity> weatherForecasts) {
		this.weatherForecasts = weatherForecasts;
	}

}
