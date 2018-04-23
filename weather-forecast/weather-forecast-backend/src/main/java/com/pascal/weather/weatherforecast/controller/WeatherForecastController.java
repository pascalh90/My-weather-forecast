package com.pascal.weather.weatherforecast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pascal.weather.weatherforecast.model.response.WeatherForecastsByLocationResponse;
import com.pascal.weather.weatherforecast.service.WeatherForecastService;

@RestController
public class WeatherForecastController {

	@Autowired
	private WeatherForecastService weatherForecastService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/weather/recent-forecasts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public  List<WeatherForecastsByLocationResponse> getRecentWeatherForecasts() {
		return weatherForecastService.getRecentWeatherForecastsPerLocation();
}
	
}
