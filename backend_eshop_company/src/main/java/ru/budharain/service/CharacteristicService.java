package ru.budharain.service;

import org.springframework.data.domain.Page;
import ru.budharain.controller.dto.CharacteristicDto;

import java.util.List;
import java.util.Optional;

public interface CharacteristicService {
    List<CharacteristicDto> findAll();

    Page<CharacteristicDto> findAll(Integer page, Integer size, String sort);

    Optional<CharacteristicDto> findById(Long id);

    void save(CharacteristicDto characteristic);

    void deleteById(Long id);
}
