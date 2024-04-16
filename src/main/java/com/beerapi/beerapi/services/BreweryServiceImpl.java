package com.beerapi.beerapi.services;

import com.beerapi.beerapi.exceptions.NotFoundException;
import com.beerapi.beerapi.models.BreweryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BreweryServiceImpl implements BreweryService {
    private Map<UUID, BreweryDTO> breweries = new HashMap<>();

    BreweryServiceImpl() {
        BreweryDTO brewery1 = BreweryDTO.builder()
                .id(UUID.randomUUID())
                .name("Singa")
                .description("don't drink alcohol")
                .score(5)
                .website_url("www.singa.com")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();
        BreweryDTO brewery2 = BreweryDTO.builder()
                .id(UUID.randomUUID())
                .name("Heineken")
                .description("crap")
                .score(2)
                .website_url("www.heineken.com")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        BreweryDTO brewery3 = BreweryDTO.builder()
                .id(UUID.randomUUID())
                .name("Carlsberg")
                .description("super crap")
                .score(3)
                .website_url("www.carlsberg.com")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        breweries.put(brewery1.getId(), brewery1);
        breweries.put(brewery2.getId(), brewery2);
        breweries.put(brewery3.getId(), brewery3);
    }

    @Override
    public List<BreweryDTO> getBreweries() {
        return new ArrayList<>(breweries.values());
    }

    @Override
    public BreweryDTO getBreweryById(UUID id) {
        var brewery = breweries.get(id);

        if (brewery == null) {
            throw new NotFoundException("Can't find a brewery with id: " + id);
        }
        return brewery;
    }

    @Override
    public BreweryDTO addBrewery(BreweryDTO brewery) {
        BreweryDTO breweryToSave = BreweryDTO.builder()
                .id(UUID.randomUUID())
                .name(brewery.getName())
                .description(brewery.getDescription())
                .score(brewery.getScore())
                .website_url(brewery.getWebsite_url())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        breweries.put(breweryToSave.getId(), breweryToSave);

        return breweryToSave;
    }

    @Override
    public void editBrewery(UUID id, BreweryDTO brewery) {
        BreweryDTO existing = getBreweryById(id);

        if (existing == null) {
            throw new NotFoundException("Can't find a brewery with id " + id);
        }

        if(brewery.getName() != null) existing.setName(brewery.getName());
        if(brewery.getDescription() != null) existing.setDescription(brewery.getDescription());
        if(brewery.getScore() != 0) existing.setScore(brewery.getScore());
        if(brewery.getWebsite_url() != null) existing.setWebsite_url(brewery.getWebsite_url());
        if(brewery.getBrewery_type() != null) existing.setBrewery_type(brewery.getBrewery_type());
        existing.setUpdateDate(LocalDateTime.now());
        existing.setVersion(existing.getVersion() + 1);

        breweries.put(existing.getId(), existing);
    }

    @Override
    public void deleteBrewery(UUID id) {
        breweries.remove(id);
    }
}
