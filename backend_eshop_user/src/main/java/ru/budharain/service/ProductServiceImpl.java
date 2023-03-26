package ru.budharain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.budharain.controller.dto.CharacteristicDto;
import ru.budharain.controller.dto.CompanyDto;
import ru.budharain.controller.dto.DiscountDto;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.model.Picture;
import ru.budharain.model.Product;
import ru.budharain.repository.ProductRepository;
import ru.budharain.repository.ProductSpecification;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<Long> companyId, Optional<Long> characteristicId,Optional<Long> discountId,Optional<Long> commentId, Optional<String> namePattern,
                                    Integer page, Integer size, String sortField) {
        Specification<Product> spec = Specification.where(null);
        if (namePattern.isPresent() && !namePattern.get().isBlank()) {
            spec = spec.and(ProductSpecification.nameLike(namePattern.get()));
        }

        if (companyId.isPresent() && companyId.get() != -1) {
            spec = spec.and(ProductSpecification.byCompany(companyId.get()));
        }
        if (characteristicId.isPresent() && characteristicId.get() != -1) {
            spec = spec.and(ProductSpecification.byCharacteristik(characteristicId.get()));
        }
        if (discountId.isPresent() && discountId.get() != -1) {
            spec = spec.and(ProductSpecification.byDiscount(discountId.get()));
        }
        if (commentId.isPresent() && commentId.get() != -1) {
            spec = spec.and(ProductSpecification.byComment(commentId.get()));
        }

        return productRepository.findAll(spec,
                        PageRequest.of(page, size, Sort.by(sortField)))
                .map(this::toProductDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(this::toProductDto);
    }

    private ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                new CompanyDto(product.getCompany().getId(),
                        product.getCompany().getName(),product.getCompany().getDescription(),
                        (Picture) product.getCompany().getLogo()
                                .stream()
                                .map(Picture ::getId)
                                .collect(Collectors.toList())),
                product.getPrice(),
                product.getStock(),
                new DiscountDto(product.getDiscount().getId(),
                        product.getDiscount().getDiscount_amount(),product.getDiscount().getDateStart(),product.getDiscount().getFinish()),
                product.getComments(),
                product.getKeyWords(),
                new CharacteristicDto(product.getCharacteristic().getId(),product.getCharacteristic().getBrand(),
                        product.getCharacteristic().getCompound(),product.getCharacteristic().getSize(),product.getCharacteristic().getColor(),
                        product.getCharacteristic().getMainCharact()),
                product.getGrade());

    }
}
