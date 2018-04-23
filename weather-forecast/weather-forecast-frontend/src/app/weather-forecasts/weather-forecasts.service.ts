import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/observable/interval';

import { WeatherForecastsByLocation } from './weatherForecastsByLocation';

@Injectable()
export class WeatherForecastsService {
    //Switch between mock file or backend URL
    //private weatherForecastUrl = './weather-forecasts.json';
    private weatherForecastUrl = 'http://localhost:8080/weather/forecast/getRecentForecasts';

    constructor(private http: HttpClient) { }

    getWeatherForecasts(): Observable<WeatherForecastsByLocation[]> {
        return Observable.timer(0, 3000)
            .concatMap(() =>
                this.http.get<WeatherForecastsByLocation[]>(this.weatherForecastUrl)
            )
            .retryWhen(error => error.delay(3000))

    }

    private handleError(err: HttpErrorResponse) {
        console.log(err.message);
        return Observable.throw(err.message);
    }
}

