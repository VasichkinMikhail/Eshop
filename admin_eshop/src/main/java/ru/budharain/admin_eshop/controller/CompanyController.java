package ru.budharain.admin_eshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.budharain.admin_eshop.controller.dto.CompanyDto;
import ru.budharain.admin_eshop.service.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {


    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public String listPage(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("companies", companyService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "company";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Company");
    }

    @GetMapping("/new")
    public String newCompanyForm(Model model) {

        model.addAttribute("company", new CompanyDto());
        return "company_form";
    }

    @GetMapping("/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {

        model.addAttribute("company", companyService.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found")));
        return "company_form";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("company") CompanyDto companyDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "company_form";
        }
        companyService.save(companyDto);
        return "redirect:/company";
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {

        companyService.deleteById(id);
        return "redirect:/company";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
