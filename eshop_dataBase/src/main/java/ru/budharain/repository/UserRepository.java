package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
