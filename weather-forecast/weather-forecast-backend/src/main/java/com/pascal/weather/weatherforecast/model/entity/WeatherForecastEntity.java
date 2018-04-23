package com.pascal.weather.weatherforecast.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WeatherForecastEntity {

	private @Id @GeneratedValue Long id;
	private String location;
	private double minTemperature;
	private boolean isMinBadWeather;
	private double maxTemperature;
	private boolean isMaxBadWeather;
	private Date date;
	private Timestamp fetchDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(Timestamp fetchDate) {
		this.fetchDate = fetchDate;
	}
	
	public double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
	public boolean isMinBadWeather() {
		return isMinBadWeather;
	}

	public void setMinBadWeather(boolean isMinBadWeather) {
		this.isMinBadWeather = isMinBadWeather;
	}

	public boolean isMaxBadWeather() {
		return isMaxBadWeather;
	}

	public void setMaxBadWeather(boolean isMaxBadWeather) {
		this.isMaxBadWeather = isMaxBadWeather;
	}

	@Override
	public String toString() {
		return "WeatherForecast [id=" + id + ", location=" + location + ", minTemperature=" + minTemperature
				+ ", isMinBadWeather=" + isMinBadWeather + ", maxTemperature=" + maxTemperature + ", isMaxBadWeather="
				+ isMaxBadWeather + ", date=" + date + ", fetchDate=" + fetchDate + "]";
	}

	
	
	

}
