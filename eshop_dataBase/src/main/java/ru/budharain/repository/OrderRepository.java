package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
