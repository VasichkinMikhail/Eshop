package ru.budharain.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.budharain.controller.dto.CommentDto;
import ru.budharain.service.CommentService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("comments", commentService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "comments";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Comment");
    }

    @GetMapping("/new")
    public String newBrandForm(Model model) {

        model.addAttribute("comment", new CommentDto());
        return "comment_form";
    }

    @GetMapping("/{id}")
    public String editBrand(@PathVariable("id") Long id, Model model) {

        model.addAttribute("comment", commentService.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found")));
        return "comment_form";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result) {

        if (result.hasErrors()) {
            return "comment_form";
        }
        commentService.save(commentDto);
        return "redirect:/comment";
    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable("id") Long id) {

        commentService.deleteById(id);
        return "redirect:/comment";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
