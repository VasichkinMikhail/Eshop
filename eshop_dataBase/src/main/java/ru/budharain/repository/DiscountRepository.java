package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Discount;


public interface DiscountRepository extends JpaRepository<Discount, Long>{

}
