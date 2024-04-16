package com.beerapi.beerapi.services;

import com.beerapi.beerapi.exceptions.NotFoundException;
import com.beerapi.beerapi.mappers.UserMapper;
import com.beerapi.beerapi.models.UserDTO;
import com.beerapi.beerapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(UUID id) {
        UserDTO userDTO = userMapper.userToUserDto(userRepository.findById(id).orElse(null));

        if (userDTO == null) {
            throw new NotFoundException("Can't find a user with id " + id);
        }

        return userDTO;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDTO)));
    }

    @Override
    public void editUser(UUID id, UserDTO userDTO) {
        userRepository.findById(id).ifPresentOrElse(foundUser -> {
            if(userDTO.getName() != null) foundUser.setName(userDTO.getName());
        }, () -> {
            throw new NotFoundException("Can't edit user with id " + id);
        });
    }

    @Override
    public void deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("Can't delete user with id " + id);
        }

    }
}
