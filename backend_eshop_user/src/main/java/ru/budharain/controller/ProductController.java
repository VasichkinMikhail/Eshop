package ru.budharain.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.service.ProductService;

import java.util.Optional;

@Tag(name = "Product", description = "Service to get product information")
@RequestMapping("/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Page<ProductDto> findAll(@RequestParam("companyId") Optional<Long> companyId,
                                    @RequestParam("characteristicId") Optional<Long> characteristicId,
                                    @RequestParam("commentId") Optional<Long> commentId,
                                    @RequestParam("discountId") Optional<Long> discountId,
                                    @RequestParam("namePattern") Optional<String> namePattern,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("sortField") Optional<String> sortField) {
        return productService.findAll(
                companyId,
                characteristicId,
                commentId,
                discountId,
                namePattern,
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id"));
    }
}
