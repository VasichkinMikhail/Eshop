package ru.budharain.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.budharain.controller.dto.ProductDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineItem implements Serializable {

    private Long productId;
    private ProductDto productDto;
    private Integer qty;


    public LineItem(ProductDto productDto) {
        this.productId = productDto.getId();
        this.productDto = productDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId);
    }
    public BigDecimal getItemTotal() {
        return productDto.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
