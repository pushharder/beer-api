package com.beerapi.beerapi.controllers;

import com.beerapi.beerapi.models.UserDTO;
import com.beerapi.beerapi.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable("id") UUID id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        var userAdded =  userService.addUser(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/user/" + userAdded.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editUser(@PathVariable("id") UUID id, @RequestBody UserDTO user) {
        userService.editUser(id, user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

}
