package homepage.exception.calendar;

public class UnableToParseFileException extends RuntimeException {

    public UnableToParseFileException(String filePath) {
        super("Unable to parse file " + filePath);
    }

}
