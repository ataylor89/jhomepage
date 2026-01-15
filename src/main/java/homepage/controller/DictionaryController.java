package homepage.controller;

import homepage.service.DictionaryService;
import homepage.model.dictionary.Dictionary;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}
