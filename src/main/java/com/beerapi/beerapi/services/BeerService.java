package com.beerapi.beerapi.services;

import com.beerapi.beerapi.models.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> getBeers();
    Beer getBeerById(UUID id);
    Beer addBeer(Beer beer);
    void editBeer(UUID id, Beer beer);
}
