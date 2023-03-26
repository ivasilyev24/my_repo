package test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class State {

    @Id()
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
