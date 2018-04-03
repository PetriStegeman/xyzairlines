package nl.xyzairlines.xyzairlines.model;

import javax.persistence.*;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long planeId;
    private int fuel;

    @ManyToOne
    private Airport airport;



}
