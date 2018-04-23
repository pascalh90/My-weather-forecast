import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { WeatherForecastsComponent } from './weather-forecasts/weather-forecasts.component';
import { WeatherForecastsService } from './weather-forecasts/weather-forecasts.service';
import { WeatherForecastDetailsComponent } from './weather-forecast-details/weather-forecast-details.component';



@NgModule({
  declarations: [
    AppComponent,
    WeatherForecastsComponent,
    WeatherForecastDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [WeatherForecastsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
