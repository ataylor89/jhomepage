package homepage.exception.weather;

import homepage.model.weather.Coordinates;

public class InvalidPointException extends RuntimeException {

    public InvalidPointException(Coordinates coordinates) {
        super("The coordinates " + coordinates.toString() + " are invalid.");
    }

}
