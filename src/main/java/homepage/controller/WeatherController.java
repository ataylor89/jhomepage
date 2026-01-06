package homepage.controller;

import homepage.service.WeatherService;
import homepage.model.weather.WeatherForecast;
import homepage.model.weather.Coordinates;
import homepage.exception.ErrorResponse;
import homepage.exception.weather.InvalidPointException;
import homepage.exception.weather.NoForecastDataException;
import homepage.exception.weather.WeatherServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data")
    public WeatherForecast weatherForecastGet(Coordinates coordinates) {
        return weatherService.getForecast(coordinates);
    }

    @PostMapping("/data")
    public WeatherForecast weatherForecastPost(Coordinates coordinates) {
        return weatherService.getForecast(coordinates);       
    }

    @ExceptionHandler({InvalidPointException.class, NoForecastDataException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(Exception ex) {
        return new ErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(WeatherServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Exception ex) {
        return new ErrorResponse(500, ex.getMessage());
    }

}
