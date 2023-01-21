package test.service;

import org.springframework.stereotype.Service;
import test.model.Suggestion;

import java.util.Map;


@Service
public class ScoreCalculator {

    public Float getScore(Suggestion suggestion, Map<String, String> requestParams) {
        String latitudeAsString = requestParams.get("latitude");
        Float latitude = latitudeAsString != null ? Float.parseFloat(latitudeAsString) : Float.MAX_VALUE;
        String  longitudeAsString = requestParams.get("longitude");
        Float longitude = longitudeAsString != null ? Float.parseFloat(longitudeAsString) : Float.MAX_VALUE;
        double val = Math.sqrt(Math.pow(latitude - suggestion.getLatitude(), 2) +
                Math.pow(longitude - suggestion.getLongitude(), 2));
        String q = requestParams.get("q");
        String name = suggestion.getName();
        int index = name.indexOf(",");
        String firstWord = index==-1 ? name: name.substring(0,index);
        if (firstWord.length()-q.length()>1) {
            return 0.3f;
        }
        if (val<2) {
            return 0.9f;
        }
        if (val<10) {
            return 0.5f;
        }
        return 0f;
    }

}
