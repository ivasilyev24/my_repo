package test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
