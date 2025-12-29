package homepage.service;

import homepage.model.WeatherForecast;
import homepage.model.ForecastPeriod;
import homepage.model.Coordinates;
import java.util.List;
import java.util.ArrayList;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public WeatherForecast getForecast(Coordinates coordinates) {
        String forecastURL = getForecastURL(coordinates);
        if (forecastURL == null) {
            return null;
        }
        WeatherForecast wf = getForecast(forecastURL);
        wf.setLatitude(coordinates.latitude());
        wf.setLongitude(coordinates.longitude());
        return wf;
    }

    public String getForecastURL(Coordinates coordinates) {
        HttpClient client = HttpClient.newHttpClient();
        String mainURL = String.format("https://api.weather.gov/points/%f,%f", coordinates.latitude(), coordinates.longitude());
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(mainURL))
            .GET()
            .build();
        String forecastURL = null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return forecastURL;
            }
            String responseBody = response.body();
            JsonReader jsonReader = javax.json.Json.createReader(new StringReader(responseBody));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            forecastURL = jsonObject.getJsonObject("properties").getString("forecast");
        } catch (Exception e) {
            System.out.println(e);
        }
        return forecastURL;
    }

    private WeatherForecast getForecast(String forecastURL) {
        WeatherForecast wf = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(forecastURL))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return wf;
            }
            String responseBody = response.body();
            wf = parse(responseBody);
        } catch (Exception e) {
            System.out.println(e);
        }
        return wf;
    }

    private WeatherForecast parse(String responseBody) {
        WeatherForecast wf = null;
        try {
            JsonReader jsonReader = javax.json.Json.createReader(new StringReader(responseBody));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            wf = new WeatherForecast();
            JsonObject data = jsonObject.getJsonObject("properties");
            JsonObject elevation = data.getJsonObject("elevation");
            if (elevation != null && elevation.getJsonNumber("value") != null) {
                wf.setElevation(elevation.getJsonNumber("value").toString() + " m");
            }
            wf.setGeneratedAt(data.getString("generatedAt", null));
            wf.setUnits(data.getString("units", null));
            wf.setPeriods(new ArrayList<ForecastPeriod>());
            if (data.getJsonArray("periods") == null) {
                return wf;
            }
            JsonArray arr = data.getJsonArray("periods");
            for (int i = 0; i < arr.size(); i++) {
                JsonObject pdata = arr.getJsonObject(i);
                ForecastPeriod fp = new ForecastPeriod();
                if (pdata.getString("name", null) != null) {
                    fp.setName(pdata.getString("name"));
                }
                if (pdata.getJsonNumber("temperature") != null && pdata.getString("temperatureUnit", null) != null) {
                    fp.setTemperature(pdata.getJsonNumber("temperature").toString() + " " + pdata.getString("temperatureUnit"));
                }
                JsonObject pop = pdata.getJsonObject("probabilityOfPrecipitation");
                if (pop != null && pop.getJsonNumber("value") != null) {
                    fp.setProbabilityOfPrecipitation(pop.getJsonNumber("value").toString() + "%");
                }
                fp.setWindDirection(pdata.getString("windDirection", null));
                fp.setWindSpeed(pdata.getString("windSpeed", null));
                fp.setShortForecast(pdata.getString("shortForecast", null));
                fp.setDetailedForecast(pdata.getString("detailedForecast", null));
                fp.setIcon(pdata.getString("icon", null));
                wf.getPeriods().add(fp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return wf;
    }
}
