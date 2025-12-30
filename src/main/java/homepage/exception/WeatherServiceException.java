package homepage.exception;

import homepage.model.Coordinates;

public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(Coordinates coordinates) {
        super("There was an internal server error while getting forecast data for coordinates " + coordinates.toString() + ".");
    }

}
