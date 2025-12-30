package homepage.service;

import homepage.model.WeatherForecast;
import homepage.model.ForecastPeriod;
import homepage.model.Coordinates;
import homepage.exception.InvalidPointException;
import homepage.exception.NoForecastDataException;
import homepage.exception.WeatherServiceException;
import java.io.IOException;
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
        if (!coordinates.valid()) {
            throw new InvalidPointException(coordinates);
        }
        try {
            String forecastURL = getForecastURL(coordinates);
            WeatherForecast weatherForecast = getForecast(forecastURL, coordinates);
            return weatherForecast;
        } catch (NoForecastDataException e) {
            throw e;
        } catch (InvalidPointException e) {
            throw e;
        } catch (Exception e) {
            throw new WeatherServiceException(coordinates);
        }
    }

    private String getForecastURL(Coordinates coordinates) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String mainURL = String.format("https://api.weather.gov/points/%f,%f", coordinates.latitude(), coordinates.longitude());
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(mainURL))
            .GET()
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return parseForecastURL(response, coordinates);
    }

    private String parseForecastURL(HttpResponse<String> response, Coordinates coordinates) {
        String responseBody = response.body();
        JsonReader jsonReader = javax.json.Json.createReader(new StringReader(responseBody));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        if (jsonObject.getInt("status", 0) == 404 && jsonObject.getString("type", "").endsWith("InvalidPoint")) {
            throw new InvalidPointException(coordinates);
        }
        JsonObject props = jsonObject.getJsonObject("properties");
        if (props == null || props.getString("forecast", null) == null) {
            throw new NoForecastDataException(coordinates);
        }
        return jsonObject.getJsonObject("properties").getString("forecast");
    }

    private WeatherForecast getForecast(String forecastURL, Coordinates coordinates) throws IOException, InterruptedException {
        WeatherForecast wf = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(forecastURL))
            .GET()
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return parseForecast(response, coordinates);
    }

    private WeatherForecast parseForecast(HttpResponse<String> response, Coordinates coordinates) {
        if (response.statusCode() != 200) {
            throw new NoForecastDataException(coordinates);
        }
        WeatherForecast wf = new WeatherForecast();
        wf.setLatitude(coordinates.latitude());
        wf.setLongitude(coordinates.longitude());
        String responseBody = response.body();
        JsonReader jsonReader = javax.json.Json.createReader(new StringReader(responseBody));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        JsonObject data = jsonObject.getJsonObject("properties");
        if (data == null || data.getJsonArray("periods") == null) {
            throw new NoForecastDataException(coordinates);
        }
        JsonObject elevation = data.getJsonObject("elevation");
        if (elevation != null && elevation.getJsonNumber("value") != null) {
            wf.setElevation(elevation.getJsonNumber("value").toString() + " m");
        }
        wf.setGeneratedAt(data.getString("generatedAt", null));
        wf.setUnits(data.getString("units", null));
        wf.setPeriods(new ArrayList<ForecastPeriod>());
        JsonArray arr = data.getJsonArray("periods");
        for (int i = 0; i < arr.size(); i++) {
            ForecastPeriod fp = new ForecastPeriod();
            JsonObject pdata = arr.getJsonObject(i);
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
        return wf;
    }
}
