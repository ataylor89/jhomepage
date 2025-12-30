package homepage.exception;

import homepage.model.Coordinates;

public class NoForecastDataException extends RuntimeException {

    public NoForecastDataException(Coordinates coordinates) {
        super("No forecast data for coordinates " + coordinates.toString() + ".");
    }

}
