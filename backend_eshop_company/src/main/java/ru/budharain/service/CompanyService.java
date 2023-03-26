package ru.budharain.service;

import org.springframework.data.domain.Page;
import ru.budharain.controller.dto.CompanyDto;

import java.util.List;
import java.util.Optional;


public interface CompanyService {
    Page<CompanyDto> findAll(Integer page, Integer size, String sortField);
    List<CompanyDto> findAll();

    Optional<CompanyDto> findById(Long id);

    void save(CompanyDto company);

    void deleteById(Long id);
}
