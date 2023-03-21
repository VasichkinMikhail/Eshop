package ru.budharain.admin_eshop.service;

import org.springframework.data.domain.Page;
import ru.budharain.admin_eshop.controller.dto.DiscountDto;

import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<DiscountDto> findAll();

    Page<DiscountDto> findAll(Integer page, Integer size, String sort);

    Optional<DiscountDto> findById(Long id);

    void save(DiscountDto discount);

    void deleteById(Long id);
}
