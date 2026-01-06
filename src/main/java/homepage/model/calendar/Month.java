package homepage.model.calendar;

import java.util.Map;

public class Month {

    private String month;
    private int days;
    private int offset;
    private Map<String, String> entries;

    public Month() {}

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setEntries(Map<String, String> entries) {
        this.entries = entries;
    }

    public Map<String, String> getEntries() {
        return entries;
    }
}
