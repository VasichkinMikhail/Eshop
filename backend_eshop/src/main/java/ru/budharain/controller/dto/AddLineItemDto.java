package ru.budharain.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddLineItemDto {

    private Long productId;

    private Integer qty;

    private String color;

    private String material;

}
