package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.model.Suggestion;
import test.repository.SuggestionRepository;
import test.repository.SuggestionRepositoryCustom;

import java.util.Map;

@RestController
public class MyController {

    @Autowired
    private SuggestionRepository suggestionRepositoryCustom;

    @GetMapping(value = "/suggestions")
    public Iterable<Suggestion> findById(@RequestParam Map<String,String> requestParams
    ) {
        String q = requestParams.get("q");
        Float latitude = Float.parseFloat(requestParams.get("latitude"));
        Float longitude = Float.parseFloat(requestParams.get("longitude"));
        Iterable<Suggestion> iterable = suggestionRepositoryCustom.findAll(requestParams);
        return iterable;
    }

}
