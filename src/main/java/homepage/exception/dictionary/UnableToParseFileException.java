package homepage.exception.dictionary;

public class UnableToParseFileException extends RuntimeException {

    public UnableToParseFileException() {
        super("Unable to parse the file static/data/dictionary/dictionary.json");
    }

}
