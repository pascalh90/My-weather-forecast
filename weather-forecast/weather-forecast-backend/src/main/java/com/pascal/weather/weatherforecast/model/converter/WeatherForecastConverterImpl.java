package com.pascal.weather.weatherforecast.model.converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;
import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastResponse;

@Component("weatherForecastConverter")
public class WeatherForecastConverterImpl implements WeatherForecastConverter {

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	@Value("${bad_weather_threshold}")
	private double badWeatherThreshold;

	@Override
	public List<WeatherForecastEntity> toWeatherForecastEntityList(
			Map<LocationResponse, WeatherForecastResponse> weatherForecastMap) {
		return weatherForecastMap.entrySet().stream().map(e -> {
			return createWeatherForecastEntity(e);
		}).collect(Collectors.toList());
	}

	private WeatherForecastEntity createWeatherForecastEntity(Entry<LocationResponse, WeatherForecastResponse> e) {
		WeatherForecastEntity entity = new WeatherForecastEntity();
		try {
			Date date = df.parse(e.getValue().getHeadline().getEffectiveDate());
			entity.setDate(new java.sql.Date(date.getTime()));
			entity.setFetchDate(new Timestamp(System.currentTimeMillis()));
			entity.setLocation(e.getKey().getLocalizedName());
			entity.setMaxTemperature(e.getValue().getDailyForecasts().get(0).getTemperature().getMaximum().getValue());
			entity.setMinTemperature(e.getValue().getDailyForecasts().get(0).getTemperature().getMinimum().getValue());
			entity.setMinBadWeather(entity.getMinTemperature() < badWeatherThreshold ? true : false);
			entity.setMaxBadWeather(entity.getMaxTemperature() < badWeatherThreshold ? true : false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return entity;
	}

}
