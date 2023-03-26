package test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {

    public Country() {}

    @Id()
    @Column(name = "id", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
