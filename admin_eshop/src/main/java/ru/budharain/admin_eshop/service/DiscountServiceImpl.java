package ru.budharain.admin_eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.budharain.admin_eshop.controller.dto.DiscountDto;
import ru.budharain.model.Discount;
import ru.budharain.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;


    @Autowired
    public DiscountServiceImpl( DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<DiscountDto> findAll() {
        return discountRepository.findAll().stream()
                .map(discount -> new DiscountDto(discount.getId(), discount.getDiscount_amount(),discount.getDateStart(),discount.getFinish()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<DiscountDto> findAll(Integer page, Integer size, String sortField) {
        return discountRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(discount -> new DiscountDto(discount.getId(), discount.getDiscount_amount(),discount.getDateStart(),discount.getFinish()));
    }

    @Override
    public Optional<DiscountDto> findById(Long id) {
        return discountRepository.findById(id)
                .map(discount -> new DiscountDto(discount.getId(), discount.getDiscount_amount(),discount.getDateStart(),discount.getFinish()));
    }


    @Override
    public void save(DiscountDto discountDto) {
        Discount discount = new Discount(discountDto.getId(), discountDto.getDiscount_amount(),discountDto.getDateStart(),discountDto.getFinish());
        discountRepository.save(discount);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }
}
