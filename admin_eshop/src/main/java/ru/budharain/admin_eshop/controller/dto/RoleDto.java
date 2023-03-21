package ru.budharain.admin_eshop.controller.dto;

import lombok.*;
import ru.budharain.model.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String name;

    private List<User> users;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
