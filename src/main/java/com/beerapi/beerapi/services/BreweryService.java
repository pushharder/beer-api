package com.beerapi.beerapi.services;

import com.beerapi.beerapi.models.BreweryDTO;

import java.util.List;
import java.util.UUID;

public interface BreweryService {
    List<BreweryDTO> getBreweries();
    BreweryDTO getBreweryById(UUID id);
    BreweryDTO addBrewery(BreweryDTO brewery);
    void editBrewery(UUID id, BreweryDTO brewery);
    void deleteBrewery(UUID id);
}
