# My-weather-forecast
A web-app that monitors weather forecast for some specific locations

## Description

This web-app contains an engine that fetch periodically weather forecast for some specific locations using AccuWeather API (https://developer.accuweather.com/apis).
It highlights the bad weather condition of specific location(s) on the web-app.

This web-app auto refresh the page to show latest forecasts and show up-to last five fetch results including for each location:
  - Fetch time
  - Forecast date
  - Mini temperature
  - Maxi temperature
  
## Technical details

- The back-end is developed in Java 8, Spring Boot, Spring data
- The front-end is developed in Angular 5, Bootstrap 4

## Configuration

The engine is configurable in the file: weather-forecast-backend/src/main/resources/application.properties
  - Interval to fetch data through weather API 
  - Locations
  - Bad weather threshold
  
## Instructions

1. Launch the back-end part:

   - cd weather-forecast-backend
   - mvn clean install
   - java -jar target/weather-forecast-backend-0.0.1-SNAPSHOT.jar
   

2. Launch the front-end part:

   - cd weather-forecast-frontend
   - yarn install
   - yarn start

3. Go to this URL is http://localhost:4200/ for the test

