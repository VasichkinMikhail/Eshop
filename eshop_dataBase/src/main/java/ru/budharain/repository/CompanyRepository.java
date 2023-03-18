package ru.budharain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.budharain.model.Company;


public interface CompanyRepository extends JpaRepository<Company, Long>{

}
