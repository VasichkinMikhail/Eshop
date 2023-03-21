package ru.budharain.admin_eshop.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.budharain.admin_eshop.controller.dto.RoleDto;
import ru.budharain.admin_eshop.controller.dto.UserDto;
import ru.budharain.model.User;
import ru.budharain.repository.RoleRepository;
import ru.budharain.repository.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUserName(), user.getMail(), user.getPassword(), user.getBalance()))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getUserName(), user.getMail(), user.getPassword(), user.getBalance(),mapRolesDto(user)));
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getMail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getBalance(),
                userDto.getRoles().stream()
                        .map(roleDto -> roleRepository.getById(roleDto.getId()))
                        .collect(Collectors.toSet()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static Set<RoleDto> mapRolesDto(User user) {
        return user.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}
