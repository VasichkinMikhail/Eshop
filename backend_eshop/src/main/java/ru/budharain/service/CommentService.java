package ru.budharain.service;

import org.springframework.data.domain.Page;
import ru.budharain.controller.dto.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDto> findAll();

    Page<CommentDto> findAll(Integer page, Integer size, String sort);

    Optional<CommentDto> findById(Long id);

    void save(CommentDto comment);

    void deleteById(Long id);
}
