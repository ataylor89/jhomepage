package homepage.exception.calendar;

public class NoDataAvailableException extends RuntimeException {

    public NoDataAvailableException(int year) {
        super("No data available for year " + year + ".");
    }

}
