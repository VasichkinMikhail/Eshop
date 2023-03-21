package ru.budharain.admin_eshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class NoticeDto {

    private Long id;
    private String title;
    private LocalDateTime time;
    private String text;
}
