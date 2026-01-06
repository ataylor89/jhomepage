package homepage.model.weather;

import java.util.List;

public class WeatherForecast {
    private double latitude;
    private double longitude;
    private String elevation;
    private String generatedAt;
    private String units;
    private List<ForecastPeriod> periods;

    public WeatherForecast() {}

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getElevation() {
        return elevation;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public void setPeriods(List<ForecastPeriod> periods) {
        this.periods = periods;
    }

    public List<ForecastPeriod> getPeriods() {
        return periods;
    }
}
