package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

}
