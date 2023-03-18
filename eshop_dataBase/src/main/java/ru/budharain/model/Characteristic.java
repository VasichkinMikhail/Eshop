package ru.budharain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String compound;
    private String size;
    private String color;
    private String mainCharact;

}
