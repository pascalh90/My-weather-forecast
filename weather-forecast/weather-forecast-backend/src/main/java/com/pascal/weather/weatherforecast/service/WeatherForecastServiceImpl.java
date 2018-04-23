package com.pascal.weather.weatherforecast.service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pascal.weather.weatherforecast.model.converter.WeatherForecastConverter;
import com.pascal.weather.weatherforecast.model.entity.WeatherForecastEntity;
import com.pascal.weather.weatherforecast.model.response.LocationResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastResponse;
import com.pascal.weather.weatherforecast.model.response.WeatherForecastsByLocationResponse;
import com.pascal.weather.weatherforecast.repository.WeatherForecastRepository;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {
	private final Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);

	@Value("${locations}")
	private String locations;

	@Value("${api_key}")
	private String apiKey;

	@Value("${location_service}")
	private String locationService;

	@Value("${forecast_service}")
	private String forecastService;

	@Autowired
	private WeatherForecastRepository weatherForecastRepository;

	@Autowired
	private WeatherForecastConverter weatherForecastConverter;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void fetchWeather() {
		// get locations
		List<LocationResponse> locationList = getLocations();

		// get weather forecasts
		Map<LocationResponse, WeatherForecastResponse> weatherForecastMap = getWeatherForecast(locationList);

		// convert response to entity
		List<WeatherForecastEntity> weatherForecastEntityList = weatherForecastConverter
				.toWeatherForecastEntityList(weatherForecastMap);

		// push to H2 database
		weatherForecastRepository.saveAll(weatherForecastEntityList);

	}

	public List<LocationResponse> getLocations() {
		List<LocationResponse> locationList = Stream.of(locations.split(";")).map(location -> {
			URI uri = buildLocationUri(location);
			logger.info("Getting location keys for '" + location + "' from: " + uri);

			return restTemplate.getForObject(uri, LocationResponse[].class);
		}).flatMap(x -> Stream.of(x)).collect(Collectors.toList());

		return locationList;
	}

	private URI buildLocationUri(String location) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(locationService).queryParam("apikey", apiKey)
				.queryParam("q", location);
		URI uri = builder.buildAndExpand().toUri();
		return uri;
	}

	public Map<LocationResponse, WeatherForecastResponse> getWeatherForecast(
			List<LocationResponse> locationResponseList) {

		Map<LocationResponse, WeatherForecastResponse> weatherForecasts = locationResponseList.stream()
				.collect(Collectors.toMap(l -> l, l -> {
					URI uri = buildWeatherForecastUri(l);
					logger.info("Getting weather forecast for location '" + l.getLocalizedName() + "', key '"
							+ l.getLocationKey() + "' from: " + uri);
					return restTemplate.getForObject(uri, WeatherForecastResponse.class);
				}));

		return weatherForecasts;
	}

	private URI buildWeatherForecastUri(LocationResponse l) {
		Map<String, Integer> uriParams = new HashMap<String, Integer>();
		uriParams.put("locationkey", l.getLocationKey());

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(forecastService).queryParam("apikey", apiKey)
				.queryParam("metric", true);
		URI uri = builder.buildAndExpand(uriParams).toUri();
		return uri;
	}

	@Override
	public List<WeatherForecastsByLocationResponse> getRecentWeatherForecastsPerLocation() {
		List<String> locationList = weatherForecastRepository.getLocationList();

		return locationList.stream().map(l -> {
			return createWeatherForecastsByLocationResponse(l);
		}).collect(Collectors.toList());
	}

	private WeatherForecastsByLocationResponse createWeatherForecastsByLocationResponse(String l) {
		WeatherForecastsByLocationResponse response = new WeatherForecastsByLocationResponse();
		response.setLocation(l);
		response.setWeatherForecasts(weatherForecastRepository.findFirst5ByLocationOrderByIdDesc(l));
		return response;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getLocationService() {
		return locationService;
	}

	public void setLocationService(String locationService) {
		this.locationService = locationService;
	}

	public String getForecastService() {
		return forecastService;
	}

	public void setForecastService(String forecastService) {
		this.forecastService = forecastService;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public WeatherForecastRepository getWeatherForecastRepository() {
		return weatherForecastRepository;
	}

	public void setWeatherForecastRepository(WeatherForecastRepository weatherForecastRepository) {
		this.weatherForecastRepository = weatherForecastRepository;
	}

}
