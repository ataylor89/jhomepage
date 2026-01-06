package homepage.model.calendar;

import java.util.List;

public class Calendar {

    private Metadata metadata;
    private int year;
    private boolean leapYear;
    private List<Month> months;    

    public Calendar() {}

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setLeapYear(boolean leapYear) {
        this.leapYear = leapYear;
    }

    public boolean isLeapYear() {
        return leapYear;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public List<Month> getMonths() {
        return months;
    }
}
