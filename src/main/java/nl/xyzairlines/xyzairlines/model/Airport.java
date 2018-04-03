package nl.xyzairlines.xyzairlines.model;

import javax.persistence.*;

@Entity
public class Airport {
    @Id
    private String name;
    @OneToMany
    private Plane plane;

}
