package ru.budharain.service;

import com.fasterxml.jackson.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartServiceImpl implements CartService {

    private final Map<LineItem, Integer> lineItems;

    public CartServiceImpl() {
        this.lineItems = new HashMap<>();
    }

    @JsonCreator
    public CartServiceImpl(@JsonProperty("lineItems") List<LineItem> lineItems) {
        this.lineItems = lineItems.stream().collect(Collectors.toMap(li -> li, LineItem::getQty));
    }
    @Override
    public void addProductQt(ProductDto productDto, int qty) {
        LineItem lineItem = new LineItem(productDto);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0)+qty);
    }

    @Override
    public void removeProduct(ProductDto productDto) {
        LineItem lineItem = new LineItem(productDto);
        lineItems.remove(lineItem);
    }
    @JsonIgnore
    @Override
    public BigDecimal getSubTotal() {
        lineItems.forEach(LineItem::setQty);
        return lineItems.keySet()
                .stream().map(LineItem::getItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems.keySet());
    }

    @Override
    public void clear() {
        lineItems.clear();
    }
}
