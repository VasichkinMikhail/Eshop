package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
