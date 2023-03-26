package test.model;

import jakarta.persistence.*;

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
    private Integer id;

    @Column
    private String name;

    @Column(name = "lat", nullable = false)
    private Float latitude;

    @Column(name = "long", nullable = false)
    private Float longitude;

    @Column
    private Float score;

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "ADMIN1")
    private State state;

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

    public State getState() {
        return state;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", score=" + score +
                ", state=" + state +
                ", country=" + country +
                '}';
    }

}
