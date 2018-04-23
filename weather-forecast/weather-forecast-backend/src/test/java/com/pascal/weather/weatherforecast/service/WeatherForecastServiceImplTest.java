package com.pascal.weather.weatherforecast.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;
import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastsByLocationResponse;
import com.pascal.weather.weatherforecast.repository.WeatherForecastRepository;

@RunWith(SpringRunner.class)
public class WeatherForecastServiceImplTest {

	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private WeatherForecastRepository weatherForecastRepository;

	@InjectMocks
	private WeatherForecastServiceImpl weatherForecastService;

	@Before
	public void init() {
		weatherForecastService = new WeatherForecastServiceImpl();
		weatherForecastService.setLocations("Paris, France");
		weatherForecastService
				.setForecastService("http://dataservice.accuweather.com/forecasts/v1/daily/1day/{locationkey}");
		weatherForecastService.setLocationService("http://dataservice.accuweather.com/locations/v1/cities/search");
		weatherForecastService.setApiKey("Mbimp1sUI3BtN9UN4dxasEAZvbGbwL6Q");
		weatherForecastService.setRestTemplate(restTemplate);
		weatherForecastService.setWeatherForecastRepository(weatherForecastRepository);
	}

	@Test
	public void test_type() throws Exception {
		assertNotNull(WeatherForecastServiceImpl.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		WeatherForecastServiceImpl target = new WeatherForecastServiceImpl();
		assertNotNull(target);
	}

	@Test
	public void test_getLocations() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		LocationResponse[] locationResponse1 = objectMapper.readValue(new File("src/test/resources/location.json"),
				LocationResponse[].class);

		Mockito.when(restTemplate.getForObject(Mockito.any(URI.class), Mockito.any())).thenReturn(locationResponse1);

		List<LocationResponse> response = weatherForecastService.getLocations();
		assertThat(response).extracting("locationKey").containsExactly(623);
		assertThat(response).extracting("localizedName").containsExactly("Paris");
	}

	@Test
	public void test_getWeatherForecast() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		LocationResponse[] locationResponse1 = objectMapper.readValue(new File("src/test/resources/location.json"),
				LocationResponse[].class);
		WeatherForecastResponse weatherForecast1 = objectMapper
				.readValue(new File("src/test/resources/forecast-paris.json"), WeatherForecastResponse.class);

		List<LocationResponse> locationResponse1List = new ArrayList<LocationResponse>();
		locationResponse1List.add(locationResponse1[0]);

		Mockito.when(restTemplate.getForObject(Mockito.any(URI.class), Mockito.any())).thenReturn(weatherForecast1);

		Map<LocationResponse, WeatherForecastResponse> response = weatherForecastService
				.getWeatherForecast(locationResponse1List);
		assertThat(response).containsKey(locationResponse1[0]);
		assertThat(response).containsValue(weatherForecast1);
	}

	@Test
	public void test_getRecentWeatherForecastsPerLocation() throws Exception {
		List<String> locationList = new ArrayList<String>();
		locationList.add("Paris");
		locationList.add("Helsinki");
		
		List<WeatherForecastEntity> weatherForecastEntityListParis = new ArrayList<WeatherForecastEntity>();
		weatherForecastEntityListParis.add(createWeatherForecastEntityParis(1L));
		weatherForecastEntityListParis.add(createWeatherForecastEntityParis(2L));
		
		List<WeatherForecastEntity> weatherForecastEntityListHelsinki = new ArrayList<WeatherForecastEntity>();
		weatherForecastEntityListHelsinki.add(createWeatherForecastEntityHelsinki(1L));
		weatherForecastEntityListHelsinki.add(createWeatherForecastEntityHelsinki(2L));
		
		Mockito.when(weatherForecastRepository.getLocationList()).thenReturn(locationList);
		
		Mockito.when(weatherForecastRepository.findFirst5ByLocationOrderByIdDesc("Paris")).thenReturn(weatherForecastEntityListParis);
		Mockito.when(weatherForecastRepository.findFirst5ByLocationOrderByIdDesc("Helsinki")).thenReturn(weatherForecastEntityListHelsinki);

		List<WeatherForecastsByLocationResponse> results = weatherForecastService.getRecentWeatherForecastsPerLocation();
		
		assertThat(results).extracting("location").containsExactly("Paris","Helsinki");
		assertThat(results).extracting("weatherForecasts").contains(weatherForecastEntityListParis,weatherForecastEntityListHelsinki);

	}

	private WeatherForecastEntity createWeatherForecastEntityParis(long id) {
		WeatherForecastEntity weatherForecastEntity = new WeatherForecastEntity();
		weatherForecastEntity.setId(id);
		weatherForecastEntity.setLocation("Paris");
		weatherForecastEntity.setMaxTemperature(25);
		weatherForecastEntity.setMinTemperature(12);
		weatherForecastEntity.setMaxBadWeather(false);
		weatherForecastEntity.setMinBadWeather(false);
		return weatherForecastEntity;
		
	}
	
	private WeatherForecastEntity createWeatherForecastEntityHelsinki(long id) {
		WeatherForecastEntity weatherForecastEntity = new WeatherForecastEntity();
		weatherForecastEntity.setId(id);
		weatherForecastEntity.setLocation("Helsinki");
		weatherForecastEntity.setMaxTemperature(15);
		weatherForecastEntity.setMinTemperature(7);
		weatherForecastEntity.setMaxBadWeather(false);
		weatherForecastEntity.setMinBadWeather(true);
		return weatherForecastEntity;
	}

}
