package test.service;

import org.springframework.stereotype.Service;
import test.model.Suggestion;

import java.util.Map;

@Service
public class ScoreCalculator {

    public boolean shouldBePresented(Suggestion suggestion, Map<String, String> requestParams) {
        String q = requestParams.get("q");
        if (q == null) {
            throw new RuntimeException();
        }
        return suggestion.getName().startsWith(q);
    }

    public Float getScore(Suggestion suggestion, Map<String, String> requestParams) {
        String latitudeAsString = requestParams.get("latitude");
        Float latitude = latitudeAsString != null ? Float.parseFloat(latitudeAsString) : Float.MAX_VALUE;
        String  longitudeAsString = requestParams.get("longitude");
        Float longitude = longitudeAsString != null ? Float.parseFloat(longitudeAsString) : Float.MAX_VALUE;
        double val = Math.sqrt(Math.pow(latitude - suggestion.getLatitude(), 2) +
                Math.pow(longitude - suggestion.getLongitude(), 2));
        if (val<5) {
            return 0.9f;
        }
        if (val<8) {
            return 0.5f;
        }
        return 0f;
    }

}
