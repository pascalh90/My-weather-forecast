import { TestBed, inject } from '@angular/core/testing';

import { WeatherForecastsService } from './weather-forecasts.service';

describe('WeatherServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WeatherForecastsService]
    });
  });

  it('should be created', inject([WeatherForecastsService], (service: WeatherForecastsService) => {
    expect(service).toBeTruthy();
  }));
});
