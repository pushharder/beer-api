package com.beerapi.beerapi.repositories;

import com.beerapi.beerapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
