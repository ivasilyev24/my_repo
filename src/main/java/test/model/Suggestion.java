package test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Suggestion {

    public Suggestion() {
    }

    public Suggestion(String name, Float latitude, Float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Id
    private String name;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @Column//(nullable = false)
    private Float score;

    public Suggestion setName(String name) {
        this.name = name;
        return this;
    }

    public Suggestion setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Suggestion setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public Suggestion setScore(Float score) {
        this.score = score;
        return this;
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
