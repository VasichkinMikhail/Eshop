package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
