package homepage.exception.dictionary;

public class UnableToReadFileException extends RuntimeException {

    public UnableToReadFileException() {
        super("Unable to read the file static/data/dictionary/dictionary.json");
    }

}
