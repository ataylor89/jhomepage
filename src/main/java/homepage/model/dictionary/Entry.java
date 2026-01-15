package homepage.model.dictionary;

import java.util.List;

public class Entry {

    private String entry;
    private List<String> definitions;
    private List<String> subjects;

    public Entry() {}

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}
