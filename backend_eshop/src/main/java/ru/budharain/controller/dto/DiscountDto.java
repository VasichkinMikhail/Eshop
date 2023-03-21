package ru.budharain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private Long id;
    private int discount_amount;
    private Date dateStart;
    private Date finish;

}
