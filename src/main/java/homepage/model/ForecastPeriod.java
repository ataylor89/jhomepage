package homepage.model;

public class ForecastPeriod {
    private String name;
    private String temperature;
    private String probabilityOfPrecipitation;
    private String windDirection;
    private String windSpeed;
    private String shortForecast;
    private String detailedForecast;
    private String icon;

    public ForecastPeriod() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setProbabilityOfPrecipitation(String probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public String getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
