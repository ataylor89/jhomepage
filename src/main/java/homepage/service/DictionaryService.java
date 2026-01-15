package homepage.service;

import homepage.model.dictionary.Dictionary;
import homepage.model.dictionary.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService { 

    public Dictionary createDictionary(List<String> subjects) {
        String path = "static/data/dictionary/dictionary.json";
        JsonObject jsonObject = readJsonFile(path);
        Dictionary dictionary = new Dictionary();
        dictionary.setName(jsonObject.getString("name"));
        dictionary.setLanguage(jsonObject.getString("language"));
        dictionary.setOrder(jsonObject.getString("order"));
        dictionary.setEntries(new ArrayList<Entry>());
        for (Entry entry : getEntries(jsonObject)) {
            if (hasOverlap(subjects, entry.getSubjects())) {
                dictionary.getEntries().add(entry);
            }           
        }
        return dictionary;
    }

    private List<Entry> getEntries(JsonObject jsonObject) {
        List<Entry> entries = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getJsonArray("entries");
        for (int i = 0; i < jsonArray.size(); i++) {
            Entry entry = new Entry();
            JsonObject entryObj = jsonArray.getJsonObject(i);
            entry.setEntry(entryObj.getString("entry"));
            entry.setDefinitions(getDefinitions(entryObj));
            entry.setSubjects(getSubjects(entryObj));
            entries.add(entry);
        }
        return entries;
    }


    private List<String> getDefinitions(JsonObject jsonObject) {
        List<String> definitions = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getJsonArray("definitions");
        for (int i = 0; i < jsonArray.size(); i++) {
            definitions.add(jsonArray.getString(i));
        }
        return definitions;
    }

    private List<String> getSubjects(JsonObject jsonObject) {
        List<String> subjects = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getJsonArray("subjects");
        for (int i = 0; i < jsonArray.size(); i++) {
            subjects.add(jsonArray.getString(i));
        }
        return subjects;
    }

    private JsonObject readJsonFile(String path) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

    private boolean hasOverlap(List<String> list1, List<String> list2) {
        Set<String> set2 = new HashSet<>(list2);
        return list1.stream().anyMatch(set2::contains);
    }

}
