package com.beerapi.beerapi.services;

import com.beerapi.beerapi.exceptions.NotFoundException;
import com.beerapi.beerapi.models.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, Beer> beers = new HashMap<>();

    @Override
    public List<Beer> getBeers() {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Beer getBeerById(UUID id) {
        var beer = beers.get(id);

        if (beer == null) {
            throw new NotFoundException("Can't find a beer with id: " + id);
        }
        return beer;
    }

    @Override
    public Beer addBeer(Beer beer) {
        Beer beerToSave = Beer.builder()
                .id(UUID.randomUUID())
                .name(beer.getName())
                .description(beer.getDescription())
                .price(beer.getPrice())
                .score(beer.getScore())
                .firstBrewed(LocalDateTime.now())
                .imageUrl(beer.getImageUrl())
                .build();

        beers.put(beerToSave.getId(), beerToSave);

        return beerToSave;
    }

    @Override
    public void editBeer(UUID id, Beer beer) {
        Beer existing = getBeerById(id);

        if(beer.getName() != null) existing.setName(beer.getName());
        if(beer.getDescription() != null) existing.setDescription(beer.getDescription());
        if(beer.getPrice() != 0) existing.setPrice(beer.getPrice());
        if(beer.getScore() != 0) existing.setScore(beer.getScore());
        if(beer.getImageUrl() != null) existing.setImageUrl(beer.getImageUrl());

        beers.put(existing.getId(), existing);
    }
}
