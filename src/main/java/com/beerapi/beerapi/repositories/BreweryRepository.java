package com.beerapi.beerapi.repositories;

import com.beerapi.beerapi.entities.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BreweryRepository extends JpaRepository<Brewery, UUID> {
}
