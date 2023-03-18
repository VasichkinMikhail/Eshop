package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long>{

}
