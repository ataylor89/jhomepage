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
        Metadata metadata = getMetadata("static/data/calendar/metadata.json");
        if (year < metadata.getMinYear() || year > metadata.getMaxYear()) {
            throw new NoDataAvailableException(year);
        }
        Calendar calendar = getCalendar("static/data/calendar/" + year + ".json");
        calendar.setMetadata(metadata);
        return calendar;
    }

    private Metadata getMetadata(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            return objectMapper.readValue(inputStream, Metadata.class);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            throw new UnableToParseFileException(path);
        } catch (IOException e) {
            System.out.println(e);
            throw new UnableToReadFileException(path);
        }
    }

    private Calendar getCalendar(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            return objectMapper.readValue(inputStream, Calendar.class);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            throw new UnableToParseFileException(path);
        } catch (IOException e) {
            System.out.println(e);
            throw new UnableToReadFileException(path);
        }
    }

}
