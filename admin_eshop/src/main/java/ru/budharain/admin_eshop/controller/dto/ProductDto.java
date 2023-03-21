package ru.budharain.admin_eshop.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.budharain.model.Comment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private DiscountDto discount;
    private List<Comment> comments = new ArrayList<>();
    private String keyWords;
    private CharacteristicDto characteristic;
    private double grade;

}
