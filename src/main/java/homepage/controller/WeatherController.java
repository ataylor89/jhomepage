package homepage.controller;

import homepage.service.WeatherService;
import homepage.model.WeatherForecast;
import homepage.model.Coordinates;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/data")
    public WeatherForecast weatherForecast(Coordinates coordinates) {
        return weatherService.getForecast(coordinates);       
    }

}
