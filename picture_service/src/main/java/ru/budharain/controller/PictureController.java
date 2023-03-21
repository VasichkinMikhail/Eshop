package ru.budharain.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.budharain.service.PictureService;

@RestController
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public ResponseEntity<byte[]> downloadPicture(@PathVariable("pictureId") long pictureId) {
        return pictureService.getPictureDataById(pictureId)
                .map(pic -> ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_TYPE, pic.getContentType())
                        .body(pic.getData()))
                .orElse(ResponseEntity
                        .notFound()
                        .build());


    }

    @DeleteMapping("/{pictureId}")
    public void deletePicture(@PathVariable("pictureId") Long pictureId) {
        pictureService.deleteById(pictureId);
    }
}
