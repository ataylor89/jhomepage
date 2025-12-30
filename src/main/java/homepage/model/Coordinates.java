package homepage.model;

import homepage.exception.InvalidPointException;

public record Coordinates(double latitude, double longitude) {
    
    public boolean valid() {
        if (latitude < -90.0 || latitude > 90.0 || longitude < -180.0 || longitude > 180.0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", latitude, longitude);
    }

}
