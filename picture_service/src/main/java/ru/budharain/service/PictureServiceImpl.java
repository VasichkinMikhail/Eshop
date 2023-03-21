package ru.budharain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.budharain.controller.PictureDto;
import ru.budharain.repository.PictureRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Value("${picture.storage.path}")
    private String storagePath;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<PictureDto> getPictureDataById(long id) {
        return pictureRepository.findById(id)
                .map(pic -> new PictureDto(pic.getContentType(), Paths.get(storagePath, pic.getStorageFileName())))
                .filter(pic -> Files.exists(pic.getPath()))
                .map(pic -> {
                    try {
                        pic.setData(Files.readAllBytes(pic.getPath()));
                        return pic;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }


    @Override
    public String createPicture(byte[] pictureData) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(storagePath, fileName))) {
            outputStream.write(pictureData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    @Override
    public void deleteById(Long id) {
        pictureRepository.deleteById(id);
    }
}
