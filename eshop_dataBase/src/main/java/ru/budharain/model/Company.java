package ru.budharain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    @OneToMany(mappedBy = "company",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Picture> logo;
    @Column
    @OneToMany(mappedBy = "company",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    @Column
    @ManyToOne(optional = false)
    private User owner;

    public Company(Long id, String name, String description, List<Picture> logo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

}
