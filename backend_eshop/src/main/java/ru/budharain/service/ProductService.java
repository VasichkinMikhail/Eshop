package ru.budharain.service;

import org.springframework.data.domain.Page;
import ru.budharain.controller.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Optional<Long> companyId, Optional<Long> characteristicId, Optional<Long> discountId, Optional<Long> commentId,
                             Optional<String> namePattern,
                             Integer page, Integer size, String sortField);

    Optional<ProductDto> findById(Long id);
}
