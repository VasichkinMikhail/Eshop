package ru.budharain.admin_eshop.service;

import ru.budharain.admin_eshop.controller.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
