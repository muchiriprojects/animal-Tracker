package com.app.animalTracker.models;


@Data
@Entity
@Table(name="sightings")
public class Sighting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String location;
    private String ranger;

}
