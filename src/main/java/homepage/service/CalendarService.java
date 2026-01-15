package homepage.service;

import homepage.model.calendar.Calendar;
import homepage.model.calendar.Metadata;
import homepage.exception.calendar.NoDataAvailableException;
import homepage.exception.calendar.UnableToReadFileException;
import homepage.exception.calendar.UnableToParseFileException;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CalendarService { 

    public Calendar getCalendar(int year) {
        String path = "static/data/calendar/metadata.json";
        Metadata metadata = (Metadata) parseJsonFile(path, Metadata.class);
        if (year < metadata.getMinYear() || year > metadata.getMaxYear()) {
            throw new NoDataAvailableException(year);
        }
        path = "static/data/calendar/" + year + ".json";
        Calendar calendar = (Calendar) parseJsonFile(path, Calendar.class);
        calendar.setMetadata(metadata);
        return calendar;
    }

    private Object parseJsonFile(String path, Class outputClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            return objectMapper.readValue(inputStream, outputClass);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            throw new UnableToParseFileException(path);
        } catch (IOException e) {
            System.out.println(e);
            throw new UnableToReadFileException(path);
        }
    }

}
