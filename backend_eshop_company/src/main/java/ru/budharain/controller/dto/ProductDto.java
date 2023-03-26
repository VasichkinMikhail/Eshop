package ru.budharain.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private CompanyDto company;
    private BigDecimal price;
    private int stock;
    private String keyWords;
    private CharacteristicDto characteristic;
    private double grade;

    public ProductDto(Long id, String name, String description, CompanyDto company, BigDecimal price,
                      int stock, String keyWords, CharacteristicDto characteristic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.price = price;
        this.stock = stock;
        this.keyWords = keyWords;
        this.characteristic = characteristic;
    }
}
