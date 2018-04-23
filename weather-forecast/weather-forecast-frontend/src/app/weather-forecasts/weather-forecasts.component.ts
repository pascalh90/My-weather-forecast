import { Component, OnInit } from '@angular/core';
import { WeatherForecastsByLocation } from './weatherForecastsByLocation';
import { WeatherForecastsService } from './weather-forecasts.service';
import {Observable} from 'rxjs/Rx';

import 'rxjs/add/operator/takeWhile';
@Component({
  selector: 'app-weather-forecasts',
  templateUrl: './weather-forecasts.component.html',
  styleUrls: ['./weather-forecasts.component.css']
})
export class WeatherForecastsComponent implements OnInit {

  constructor(private weatherForecastsService: WeatherForecastsService) { }
  weatherForecastsByLocation: WeatherForecastsByLocation[];
  errorMessage: string;

  ngOnInit() {
    this.getWeatherForecasts();
  }

  getWeatherForecasts(): void {

    this.weatherForecastsService.getWeatherForecasts().subscribe(forecasts => {
      this.weatherForecastsByLocation = forecasts;
    }, error => this.errorMessage = <any>error);
  }

}
