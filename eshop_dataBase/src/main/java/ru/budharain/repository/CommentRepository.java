package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
