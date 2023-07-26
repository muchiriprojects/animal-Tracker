package com.app.animalTracker.models;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name="animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String health;
    private String age;

}
