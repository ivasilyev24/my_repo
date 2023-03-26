package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import test.model.SuggestionDTO;
import test.repository.SuggestionRepository;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

@RestController
public class MyController {

    @Autowired
    private SuggestionRepository suggestionRepositoryCustom;

    @GetMapping(value = "/suggestions")
    public Iterable<SuggestionDTO> findById(@RequestParam Map<String, String> requestParams
    ) {
        String q = requestParams.get("q");
        String latitudeAsString = requestParams.get("latitude");
        if (latitudeAsString != null) {
            Float latitude = Float.parseFloat(latitudeAsString);
        }
        String longitudeAsString = requestParams.get("longitude");
        if (longitudeAsString != null) {
            Float longitude = Float.parseFloat(longitudeAsString);
        }
        Iterable<SuggestionDTO> iterable = suggestionRepositoryCustom.findAll(requestParams);
        return iterable;
    }

    @ExceptionHandler(Exception.class)
    public ResponseStatusException handleException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.BAD_REQUEST,  s);
        ex.setStackTrace(new StackTraceElement[] {});
        return ex;
    }

}
