package com.beerapi.beerapi.services;

import com.beerapi.beerapi.models.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getUsers();
    UserDTO getUser(UUID id);
    UserDTO addUser(UserDTO user);
    void editUser(UUID id, UserDTO user);
    void deleteUser(UUID id);
}
