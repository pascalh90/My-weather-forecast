import { Time } from "@angular/common";

export class WeatherForecastsByLocation{
  location: string;
  weatherForecasts: WeatherForecast[];
}

export class WeatherForecast {
  id: number;
  location: string;
  minTemperature: number;
  minBadWeather: string;
  maxTemperature: number;
  maxBadWeather: boolean;
  date: string;
  fetchDate: string;
}