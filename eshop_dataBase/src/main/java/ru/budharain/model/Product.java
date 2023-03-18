package ru.budharain.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(length = 65535, columnDefinition = "LONGTEXT")
    private String description;

    private Company company;

    private BigDecimal price;

    private int stock;

    private Discount discount;

    private List<Comment> comments = new ArrayList<>();

    private String keyWords;

    private Characteristic characteristic;

    private double grade;


}
