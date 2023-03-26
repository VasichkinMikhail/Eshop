package ru.budharain.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.service.CharacteristicService;
import ru.budharain.service.CompanyService;
import ru.budharain.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@Tag(name = "Product", description = "Service to get product information")
@RequestMapping("/v1/product_user")
@RestController
public class ProductController {

    private final ProductService productService;

    private final CompanyService companyService;

    private final CharacteristicService characteristicService;

    @Autowired
    public ProductController(ProductService productService,
                             CompanyService companyService, CharacteristicService characteristicService) {
        this.productService = productService;
        this.companyService = companyService;
        this.characteristicService = characteristicService;
    }

    @GetMapping
    public String listPage(
            @RequestParam("companyId") Optional<Long> companyId,
            @RequestParam("characteristicId") Optional<Long> characteristicId,
            @RequestParam("namePattern") Optional<String> namePattern,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("characteristics", characteristicService.findAll());
        model.addAttribute("products", productService.findAll(
                companyId,
                characteristicId,
                namePattern,
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "product_user";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Product");
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {

        model.addAttribute("product", new ProductDto());
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("characteristics", characteristicService.findAll());
        return "product_form_user";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("characteristics", characteristicService.findAll());
        return "product_form_user";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.findAll());
            model.addAttribute("characteristics", characteristicService.findAll());
            return "product_form_user";
        }
        productService.save(product);
        return "redirect:/product_user";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {

        productService.deleteById(id);
        return "redirect:/product_user";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
