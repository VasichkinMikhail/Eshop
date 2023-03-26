package ru.budharain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.budharain.controller.dto.CompanyDto;
import ru.budharain.model.Company;
import ru.budharain.model.Picture;
import ru.budharain.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<CompanyDto> findAll(Integer page, Integer size, String sortField) {
        return companyRepository.findAll(
                        PageRequest.of(page, size, Sort.by(sortField)))
                .map(CompanyServiceImpl::convertToDto);
    }

    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream()
                .map(company -> new CompanyDto(company.getId(), company.getName(), company.getDescription(), (Picture) company.getLogo().stream().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanyDto> findById(Long id) {
        return companyRepository.findById(id)
                .map(CompanyServiceImpl::convertToDto);
    }

    @Override
    public void save(CompanyDto companyDto) {
        Company company = new Company(companyDto.getId(),
                companyDto.getName(),
                companyDto.getDescription(),
                (List<Picture>) companyDto.getLogo());
        companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    private static CompanyDto convertToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getDescription(),
                (Picture) company.getLogo().
                        stream().
                        collect(Collectors.toList())
        );

    }
}
