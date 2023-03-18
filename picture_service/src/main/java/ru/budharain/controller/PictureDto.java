package ru.budharain.controller;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
@Getter
@Setter
public class PictureDto {

    private String contentType;

    private Path path;
    private byte[] data;

    public PictureDto(String contentType, Path path) {
        this.contentType = contentType;
        this.path = path;
    }
}
