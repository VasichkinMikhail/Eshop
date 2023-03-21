package ru.budharain.admin_eshop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.budharain.model.User;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private User user;

}
