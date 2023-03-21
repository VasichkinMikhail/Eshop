package ru.budharain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.budharain.service.dto.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllCartDto implements Serializable {

    private List<LineItem> lineItems;
    private BigDecimal subtotal;
}
