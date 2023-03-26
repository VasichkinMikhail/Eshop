package ru.budharain.admin_eshop.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.budharain.admin_eshop.controller.dto.ProductDto;
import ru.budharain.admin_eshop.service.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    private final CompanyService companyService;

    private final CharacteristicService characteristicService;

    private final DiscountService discountService;

    private final CommentService commentService;

    @Autowired
    public ProductController(ProductService productService,
                             CompanyService companyService, CharacteristicService characteristicService, DiscountService discountService, CommentService commentService) {
        this.productService = productService;
        this.companyService = companyService;
        this.characteristicService = characteristicService;
        this.discountService = discountService;
        this.commentService = commentService;
    }

    @GetMapping
    public String listPage(
            @RequestParam("companyId") Optional<Long> companyId,
            @RequestParam("discountId") Optional<Long> discountId,
            @RequestParam("characteristicId") Optional<Long> characteristicId,
            @RequestParam("comments") Optional<Long> commentId,
            @RequestParam("namePattern") Optional<String> namePattern,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("characteristics", characteristicService.findAll());
        model.addAttribute("discounts", discountService.findAll());
        model.addAttribute("comments", commentService.findAll());
        model.addAttribute("products", productService.findAll(
                companyId,
                characteristicId,
                discountId,
                commentId,
                namePattern,
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "product";
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
        model.addAttribute("discounts", discountService.findAll());
        model.addAttribute("comments", commentService.findAll());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("characteristics", characteristicService.findAll());
        model.addAttribute("discounts", discountService.findAll());
        model.addAttribute("comments", commentService.findAll());
        return "product_form";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.findAll());
            model.addAttribute("characteristics", characteristicService.findAll());
            model.addAttribute("discounts", discountService.findAll());
            model.addAttribute("comments", commentService.findAll());
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {

        productService.deleteById(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
