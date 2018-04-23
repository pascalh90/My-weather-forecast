package com.pascal.weather.weatherforecast.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.pascal.weather.weatherforecast.service.WeatherForecastService;

@Configuration
public class WeatherForecastSchedulerConfiguration {

	@Value("${fetch_interval}")
	private int timeInterval; 
	@Value("${start_delay}")
	private int startDelay; 
	
	@Autowired
	private WeatherForecastService weatherForecastService;
	
	@Bean
	public MethodInvokingJobDetailFactoryBean fetchWeatherJobDetails() {
		MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		methodInvokingJobDetailFactoryBean.setTargetObject(weatherForecastService);
		methodInvokingJobDetailFactoryBean.setTargetMethod("fetchWeather");
		return methodInvokingJobDetailFactoryBean;
		
	}
	
	@Bean
	public SimpleTriggerFactoryBean fetchWeatherIntervalTrigger() {
		SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
		simpleTriggerFactoryBean.setJobDetail(fetchWeatherJobDetails().getObject());
		simpleTriggerFactoryBean.setStartDelay(startDelay);
		simpleTriggerFactoryBean.setRepeatInterval(timeInterval);
		return simpleTriggerFactoryBean;
		
	}
	
	@Bean
	public SchedulerFactoryBean fetchWeatherScheduler() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobDetails(fetchWeatherJobDetails().getObject());
		schedulerFactoryBean.setTriggers(fetchWeatherIntervalTrigger().getObject());
		return schedulerFactoryBean;
	}
}
