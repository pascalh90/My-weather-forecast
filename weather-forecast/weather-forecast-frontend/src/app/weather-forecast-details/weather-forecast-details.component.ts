import { Component, OnInit, Input } from '@angular/core';
import { WeatherForecastsByLocation } from '../weather-forecasts/weatherForecastsByLocation';

@Component({
  selector: 'app-weather-forecast-details',
  templateUrl: './weather-forecast-details.component.html',
  styleUrls: ['./weather-forecast-details.component.css']
})
export class WeatherForecastDetailsComponent implements OnInit {
  @Input() weatherForecastsByLocation: WeatherForecastsByLocation;
  constructor() { }

  ngOnInit() {
  }

}
