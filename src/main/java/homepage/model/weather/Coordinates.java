package homepage.model.weather;

import homepage.exception.weather.InvalidPointException;

public record Coordinates(double latitude, double longitude) {
    
    public boolean valid() {
        if (latitude < -90.0 || latitude > 90.0 || longitude < -180.0 || longitude > 180.0) {
            return false;
        }
        return true;
    }

    private String format(double num) {
        if (num % 1 == 0) {
            return String.valueOf((int) num);
        }
        return String.valueOf(num);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", format(latitude), format(longitude));
    }

}
