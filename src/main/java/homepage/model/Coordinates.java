package homepage.model;

public record Coordinates(double latitude, double longitude) {
    
    public Coordinates {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("The latitude must be between -90 degrees and 90 degrees.");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("The longitude must be between -180 degrees and 180 degrees.");
        }
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", latitude, longitude);
    }

}
