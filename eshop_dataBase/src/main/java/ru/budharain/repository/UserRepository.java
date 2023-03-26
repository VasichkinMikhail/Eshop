package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.budharain.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.roles " +
            "where u.userName = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
