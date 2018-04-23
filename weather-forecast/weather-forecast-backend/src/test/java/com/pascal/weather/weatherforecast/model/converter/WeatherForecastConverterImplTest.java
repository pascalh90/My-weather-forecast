package com.pascal.weather.weatherforecast.model.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;
import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastResponse;

@RunWith(SpringRunner.class)
public class WeatherForecastConverterImplTest {

	private WeatherForecastConverterImpl weatherForecastConverter;

	@Before
	public void init() {
		weatherForecastConverter = new WeatherForecastConverterImpl();
	}

	@Test
	public void test_toWeatherForecastEntityList() throws Exception {
		Map<LocationResponse, WeatherForecastResponse> weatherForecastMap = createWeatherForecastMap();
		List<WeatherForecastEntity> weatherForecastEntityList = weatherForecastConverter
				.toWeatherForecastEntityList(weatherForecastMap);
		assertThat(weatherForecastEntityList).extracting("location").containsExactly("Paris");
		assertThat(weatherForecastEntityList).extracting("minTemperature").containsExactly(15.0);
		assertThat(weatherForecastEntityList).extracting("isMinBadWeather").containsExactly(false);
		assertThat(weatherForecastEntityList).extracting("maxTemperature").containsExactly(28.9);
		assertThat(weatherForecastEntityList).extracting("isMaxBadWeather").containsExactly(false);

	}

	private Map<LocationResponse, WeatherForecastResponse> createWeatherForecastMap()
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		LocationResponse[] locationResponse1 = objectMapper.readValue(new File("src/test/resources/location.json"),
				LocationResponse[].class);
		WeatherForecastResponse weatherForecast1 = objectMapper
				.readValue(new File("src/test/resources/forecast-paris.json"), WeatherForecastResponse.class);

		Map<LocationResponse, WeatherForecastResponse> weatherForecastMap = new HashMap<LocationResponse, WeatherForecastResponse>();
		weatherForecastMap.put(locationResponse1[0], weatherForecast1);
		return weatherForecastMap;
	}

}
