package homepage.service;

import homepage.model.calendar.Calendar;
import homepage.model.calendar.Metadata;
import homepage.model.calendar.Month;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;
import org.springframework.stereotype.Service;

@Service
public class CalendarService { 

    public Calendar getCalendar(int year) {
        Calendar calendar = new Calendar();
        calendar.setMetadata(getMetadata());
        String path = "static/data/calendar/" + year + ".json";
        JsonObject jsonObject = readJsonFile(path);
        calendar.setYear(jsonObject.getInt("year"));
        calendar.setLeapYear(jsonObject.getBoolean("leap_year"));
        calendar.setMonths(getMonths(jsonObject));
        return calendar;
    }

    private Metadata getMetadata() {
        String path = "static/data/calendar/metadata.json";
        JsonObject jsonObject = readJsonFile(path);
        Metadata metadata = new Metadata();
        metadata.setMinYear(jsonObject.getInt("min_year"));
        metadata.setMaxYear(jsonObject.getInt("max_year"));
        return metadata;
    }

    private List<Month> getMonths(JsonObject jsonObject) {
        List<Month> months = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getJsonArray("months");
        for (int i = 0; i < jsonArray.size(); i++) {
            Month month = new Month();
            JsonObject monthObj = jsonArray.getJsonObject(i);
            month.setMonth(monthObj.getString("month"));
            month.setDays(monthObj.getInt("days"));
            month.setOffset(monthObj.getInt("offset"));
            Map<String, String> entries = new HashMap<>();
            JsonObject entriesObj = monthObj.getJsonObject("entries");
            for (String key : entriesObj.keySet()) {
                entries.put(key, entriesObj.getString(key));
            }
            month.setEntries(entries);
            months.add(month);
        }
        return months;
    }

    private JsonObject readJsonFile(String path) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

}
