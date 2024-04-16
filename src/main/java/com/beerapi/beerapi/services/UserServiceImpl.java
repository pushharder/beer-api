package com.beerapi.beerapi.services;

import com.beerapi.beerapi.exceptions.NotFoundException;
import com.beerapi.beerapi.models.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private Map<UUID, UserDTO> usersMap = new HashMap<>();

    UserServiceImpl () {
        UserDTO user1 = UserDTO.builder()
                .id(UUID.randomUUID())
                .name("Alex")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();
        UserDTO user2 = UserDTO.builder()
                .id(UUID.randomUUID())
                .name("Vasya")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        UserDTO user3 = UserDTO.builder()
                .id(UUID.randomUUID())
                .name("Petya")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        usersMap.put(user1.getId(), user1);
        usersMap.put(user2.getId(), user2);
        usersMap.put(user3.getId(), user3);
    }

    @Override
    public List<UserDTO> getUsers() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public UserDTO getUser(UUID id) {
        UserDTO user = usersMap.get(id);

        if (user == null) {
            throw new NotFoundException("Can't find a user with id: " + id);
        }

        return user;
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        UserDTO userToAdd = UserDTO.builder()
                .id(UUID.randomUUID())
                .name(user.getName())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        usersMap.put(userToAdd.getId(), userToAdd);

        return userToAdd;
    }

    @Override
    public void editUser(UUID id, UserDTO user) {
        UserDTO existing = getUser(id);

        if(user.getName() != null) existing.setName(user.getName());
        existing.setUpdateDate(LocalDateTime.now());
        existing.setVersion(existing.getVersion() + 1);

        usersMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteUser(UUID id) {
        usersMap.remove(id);
    }
}
