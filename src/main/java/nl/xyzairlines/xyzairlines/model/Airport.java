package nl.xyzairlines.xyzairlines.model;

import javax.persistence.*;

@Entity
public class Airport {
    @Id
    private String name;
    @OneToMany
    private Plane plane;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
