package com.beerapi.beerapi.services;

import com.beerapi.beerapi.exceptions.NotFoundException;
import com.beerapi.beerapi.mappers.BreweryMapper;
import com.beerapi.beerapi.models.BreweryDTO;
import com.beerapi.beerapi.repositories.BreweryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BreweryServiceJPA implements BreweryService {
    private final BreweryRepository breweryRepository;
    private final BreweryMapper breweryMapper;

    @Override
    public List<BreweryDTO> getBreweries() {
        return breweryRepository.findAll()
                .stream()
                .map(breweryMapper::brewertyToBreweryDto)
                .collect(Collectors.toList());
    }

    @Override
    public BreweryDTO getBreweryById(UUID id) {
        BreweryDTO breweryDTO = breweryMapper.brewertyToBreweryDto(breweryRepository.findById(id).orElse(null));

        if (breweryDTO == null) {
            throw new NotFoundException("Can't find a brewery with id " + id);
        }

        return breweryDTO;
    }

    @Override
    public BreweryDTO addBrewery(BreweryDTO brewery) {
        return breweryMapper.brewertyToBreweryDto(breweryRepository.save(breweryMapper.breweryDtoToBrewery(brewery)));
    }

    @Override
    public void editBrewery(UUID id, BreweryDTO brewery) {
        breweryRepository.findById(id).ifPresentOrElse(foundBrewery -> {
            if(brewery.getName() != null) foundBrewery.setName(brewery.getName());
            if(brewery.getDescription() != null) foundBrewery.setDescription(brewery.getDescription());
            if(brewery.getScore() != 0) foundBrewery.setScore(brewery.getScore());
            if(brewery.getWebsite_url() != null) foundBrewery.setWebsite_url(brewery.getWebsite_url());
            if(brewery.getBrewery_type() != null) foundBrewery.setWebsite_url(brewery.getBrewery_type());
        }, () -> {
            throw new NotFoundException("Can't update a brewery with id" + id);
        });
    }

    @Override
    public void deleteBrewery(UUID id) {
        if (breweryRepository.existsById(id)) {
            breweryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Can't delete a brewery with id" + id);
        }
    }
}
