package homepage.controller;

import homepage.service.DictionaryService;
import homepage.model.dictionary.Dictionary;
import homepage.exception.ErrorResponse;
import homepage.exception.dictionary.UnableToReadFileException;
import homepage.exception.dictionary.UnableToParseFileException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/create")
    public Dictionary createDictionaryGet(@RequestParam("subjects") List<String> subjects) {
        return dictionaryService.createDictionary(subjects);
    }

    @PostMapping("/create")
    public Dictionary createDictionaryPost(@RequestParam("subjects") List<String> subjects) {
        return dictionaryService.createDictionary(subjects);
    }

    @ExceptionHandler(UnableToReadFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnableToReadFile(UnableToReadFileException ex) {
        return new ErrorResponse(500, ex.getMessage());
    }

    @ExceptionHandler(UnableToParseFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnableToReadFile(UnableToParseFileException ex) {
        return new ErrorResponse(500, ex.getMessage());
    }

}
