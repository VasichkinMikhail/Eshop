package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.budharain.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("select distinct o " +
            "from Order o " +
            "inner join fetch o.user u " +
            "inner join fetch o.orderLineItems i " +
            "where u.userName = :username")
    List<Order> findAllByUsername(String username);

}
