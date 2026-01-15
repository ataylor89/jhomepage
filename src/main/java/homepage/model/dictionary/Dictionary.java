package homepage.model.dictionary;

import homepage.model.dictionary.Entry;
import java.util.List;

public class Dictionary {
    
    private String name;
    private String language;
    private String order;
    private List<Entry> entries;

    public Dictionary() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }

}
