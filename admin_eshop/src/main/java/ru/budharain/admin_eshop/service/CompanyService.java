package ru.budharain.admin_eshop.service;

import org.springframework.data.domain.Page;
import ru.budharain.admin_eshop.controller.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDto> findAll();

    Page<CompanyDto> findAll(Integer page, Integer size, String sort);

    Optional<CompanyDto> findById(Long id);

    void save(CompanyDto company);

    void deleteById(Long id);
}
