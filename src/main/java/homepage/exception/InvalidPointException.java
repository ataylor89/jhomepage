package homepage.exception;

import homepage.model.Coordinates;

public class InvalidPointException extends RuntimeException {

    public InvalidPointException(Coordinates coordinates) {
        super("The coordinates " + coordinates.toString() + " are invalid.");
    }

}
