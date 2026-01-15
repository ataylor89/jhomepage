package homepage.exception.calendar;

public class UnableToReadFileException extends RuntimeException {

    public UnableToReadFileException(String filePath) {
        super("Unable to read file " + filePath);
    }

}
