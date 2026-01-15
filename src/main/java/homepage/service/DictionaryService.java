package homepage.service;

import homepage.model.dictionary.Dictionary;
import homepage.model.dictionary.Entry;
import homepage.exception.dictionary.UnableToReadFileException;
import homepage.exception.dictionary.UnableToParseFileException;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService { 

    public Dictionary createDictionary(List<String> subjects) {
        Dictionary dictionary = getFullDictionary();
        filter(dictionary, subjects);
        return dictionary;
    }

    private Dictionary getFullDictionary() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String path = "static/data/dictionary/dictionary.json";
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            return objectMapper.readValue(inputStream, Dictionary.class);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            throw new UnableToParseFileException();
        } catch (IOException e) {
            System.out.println(e);
            throw new UnableToReadFileException();
        }
    }

    private void filter(Dictionary dictionary, List<String> subjects) {
        List<Entry> matchingEntries = dictionary.getEntries().stream().filter(e -> pertainsTo(e, subjects)).toList();
        dictionary.setEntries(matchingEntries);
    }

    private boolean pertainsTo(Entry entry, List<String> subjects) {
        Set<String> set = new HashSet<>(subjects);
        return entry.getSubjects().stream().anyMatch(set::contains);
    }

}
