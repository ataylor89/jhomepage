package homepage.exception.weather;

import homepage.model.weather.Coordinates;

public class NoForecastDataException extends RuntimeException {

    public NoForecastDataException(Coordinates coordinates) {
        super("No forecast data for coordinates " + coordinates.toString() + ".");
    }

}
