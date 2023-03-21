package ru.budharain.service;

import ru.budharain.controller.dto.ProductDto;
import ru.budharain.service.dto.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addProductQt(ProductDto productDto,int qty);
    void removeProduct(ProductDto productDto);
    List<LineItem> getLineItems();
    BigDecimal getSubTotal();
    void clear();
}
