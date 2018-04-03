package nl.xyzairlines.xyzairlines.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Airport {

    private String name;
    @OneToMany
    private Plane plane;

}
