package com.pascal.weather.weatherforecast.model.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class WeatherForecastEntityTest {
	
	private static final Timestamp FETCH_DATE = new Timestamp(255);
	private static final int MIN_TEMPERATURE_2 = 2;
	private static final int MAX_TEMPERATURE_20 = 20;
	private static final String LOCATION_PARIS = "Paris";
	private static final long Id_1L = 1L;
	private WeatherForecastEntity weatherForecastEntity;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private java.sql.Date date = null; 
	
	@Before
	public void init() throws ParseException {
		date = new java.sql.Date(df.parse("2018-04-22T20:00:00+02:00").getTime());
		weatherForecastEntity = new WeatherForecastEntity();
		weatherForecastEntity.setDate(date);
		weatherForecastEntity.setId(Id_1L);
		weatherForecastEntity.setLocation(LOCATION_PARIS);
		weatherForecastEntity.setMaxBadWeather(false);
		weatherForecastEntity.setMaxTemperature(MAX_TEMPERATURE_20);
		weatherForecastEntity.setMinBadWeather(true);
		weatherForecastEntity.setMinTemperature(MIN_TEMPERATURE_2);
		weatherForecastEntity.setFetchDate(FETCH_DATE);
	}
	
	@Test
	public void testGetDate() {
		assertThat(weatherForecastEntity.getDate()).isEqualTo(date);
	}
	
	@Test
	public void testGetId() {
		assertThat(weatherForecastEntity.getId()).isEqualTo(Id_1L);
	}
	
	@Test
	public void testGetLocation() {
		assertThat(weatherForecastEntity.getLocation()).isEqualTo(LOCATION_PARIS);
	}
	
	@Test
	public void testGetMaxBadWeather() {
		assertThat(weatherForecastEntity.isMaxBadWeather()).isEqualTo(false);
	}
	
	@Test
	public void testGetMaxTemperature() {
		assertThat(weatherForecastEntity.getMaxTemperature()).isEqualTo(MAX_TEMPERATURE_20);
	}
	
	@Test
	public void testGetMinBadWeather() {
		assertThat(weatherForecastEntity.isMinBadWeather()).isEqualTo(true);
	}
	
	@Test
	public void testGetMinTemperature() {
		assertThat(weatherForecastEntity.getMinTemperature()).isEqualTo(MIN_TEMPERATURE_2);
	}
	
	@Test
	public void testGetFetchDate() {
		assertThat(weatherForecastEntity.getFetchDate()).isEqualTo(FETCH_DATE);
	}
}
