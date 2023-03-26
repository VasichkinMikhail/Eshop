package ru.budharain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.budharain.controller.NotFoundException;
import ru.budharain.controller.dto.CharacteristicDto;
import ru.budharain.controller.dto.CompanyDto;
import ru.budharain.controller.dto.ProductDto;
import ru.budharain.model.*;
import ru.budharain.repository.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CharacteristikRepository characteristikRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CharacteristikRepository characteristikRepository, CompanyRepository companyRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.characteristikRepository = characteristikRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public Page<ProductDto> findAll(Optional<Long> companyId, Optional<Long> characteristicId, Optional<String> namePattern,
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

        return productRepository.findAll(spec,
                        PageRequest.of(page, size, Sort.by(sortField)))
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        Product product = (productDto.getId() != null) ? productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("")) : new Product();
        Company company = companyRepository.findById(productDto.getCompany().getId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        Characteristic characteristic = characteristikRepository.findById(productDto.getCharacteristic().getId())
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));

        product.setName(productDto.getName());
        product.setCompany(company);
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setCharacteristic(characteristic);
        product.setGrade(product.getGrade());
        product.setKeyWords(product.getKeyWords());
        product.setDescription(productDto.getDescription());

        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto convertToDto(Product product) {
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
                product.getKeyWords(),
                new CharacteristicDto(product.getCharacteristic().getId(),product.getCharacteristic().getBrand(),
                        product.getCharacteristic().getCompound(),product.getCharacteristic().getSize(),product.getCharacteristic().getColor(),
                        product.getCharacteristic().getMainCharact()));

    }
}
