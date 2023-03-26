package ru.budharain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.budharain.controller.dto.CharacteristicDto;
import ru.budharain.model.Characteristic;
import ru.budharain.repository.CharacteristikRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristikRepository characteristikRepository;


    @Autowired
    public CharacteristicServiceImpl(CharacteristikRepository characteristikRepository) {
        this.characteristikRepository = characteristikRepository;
    }

    @Override
    public List<CharacteristicDto> findAll() {
        return characteristikRepository.findAll().stream()
                .map(character -> new CharacteristicDto(character.getId(), character.getMainCharact(), character.getCompound(),
                        character.getSize(), character.getColor(), character.getBrand()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CharacteristicDto> findAll(Integer page, Integer size, String sortField) {
        return characteristikRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(character -> new CharacteristicDto(character.getId(), character.getMainCharact(), character.getCompound(),
                        character.getSize(), character.getColor(), character.getBrand()));
    }

    @Override
    public Optional<CharacteristicDto> findById(Long id) {
        return characteristikRepository.findById(id)
                .map(character -> new CharacteristicDto(character.getId(), character.getMainCharact(), character.getCompound(),
                        character.getSize(), character.getColor(), character.getBrand()));
    }


    @Override
    public void save(CharacteristicDto characteristicDto) {
        Characteristic characteristic = new Characteristic(characteristicDto.getId(), characteristicDto.getMainCharact(), characteristicDto.getCompound(),
                characteristicDto.getSize(), characteristicDto.getColor(), characteristicDto.getBrand());
        characteristikRepository.save(characteristic);
    }

    @Override
    public void deleteById(Long id) {
        characteristikRepository.deleteById(id);
    }
}
