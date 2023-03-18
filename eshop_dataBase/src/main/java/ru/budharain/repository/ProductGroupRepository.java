package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>{

}
