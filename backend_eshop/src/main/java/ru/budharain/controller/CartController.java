package ru.budharain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.budharain.controller.dto.AddLineItemDto;
import ru.budharain.controller.dto.AllCartDto;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.service.CartService;
import ru.budharain.service.ProductService;
import ru.budharain.service.dto.LineItem;

import java.util.List;

@RequestMapping("/v1/cart")
@RestController
public class CartController {


    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<LineItem> addToCart(@RequestBody AddLineItemDto addLineItemDto) {
        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQt(productDto,addLineItemDto.getQty());
        return cartService.getLineItems();
    }

    @DeleteMapping(consumes = "application/json")
    public void deleteLineItem(@RequestBody LineItem lineItem) {
        cartService.removeProduct(lineItem.getProductDto());
    }

    @GetMapping("/all")
    public AllCartDto findAll() {
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }
}
