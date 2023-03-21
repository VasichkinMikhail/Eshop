package ru.budharain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDto {

    private Long id;

    private String brand;
    private String compound;
    private String size;
    private String color;
    private String mainCharact;

}
