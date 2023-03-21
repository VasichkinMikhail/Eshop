package ru.budharain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(length = 65535, columnDefinition = "LONGTEXT")
    private String description;
    @ManyToOne(optional = false)
    private Company company;
    @Column
    private BigDecimal price;
    @Column
    private int stock;
    @Column
    @ManyToOne(optional = false)
    private Discount discount;
    @OneToMany(mappedBy = "product",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String keyWords;
    @OneToOne(optional = false)
    private Characteristic characteristic;
    @Column
    private double grade;


}
