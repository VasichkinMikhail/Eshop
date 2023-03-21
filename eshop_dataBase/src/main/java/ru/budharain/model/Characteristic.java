package ru.budharain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String brand;
    @Column
    private String compound;
    @Column
    private String size;
    @Column
    private String color;
    @Column
    private String mainCharact;
    @Column
    @OneToOne(optional = false)
    private Product products;

    public Characteristic(Long id, String brand, String compound, String size, String color, String mainCharact) {
        this.id = id;
        this.brand = brand;
        this.compound = compound;
        this.size = size;
        this.color = color;
        this.mainCharact = mainCharact;
    }
}
