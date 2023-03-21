package ru.budharain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private LocalDateTime orderDate;

    private String status;

    private String username;

    private List<OrderLineItemDto> orderLineItems;
}
