package ru.budharain.admin_eshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.budharain.model.Company;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {

    private Long id;
    private String name;
    private String contentType;
    private String storageFileName;
    private Company company;

    public PictureDto(Long id, String name, String contentType, String storageFileName) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.storageFileName = storageFileName;
    }
}
