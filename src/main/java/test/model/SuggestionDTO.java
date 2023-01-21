package test.model;

/**
 * Возвращаемый обект
 */
public class SuggestionDTO {

    private String name;

    private Float latitude;

    private Float longitude;

    private Float score;

    public SuggestionDTO(Suggestion s) {
        name = s.getName() + ", " + s.getState().getName() + ", " + s.getCountry().getName();
        latitude = s.getLatitude();
        longitude = s.getLongitude();
        score = s.getScore();
    }

    public String getName() {
        return name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", score=" + score +
                '}';
    }

}
