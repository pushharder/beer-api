package com.beerapi.beerapi.mappers;

import com.beerapi.beerapi.entities.Brewery;
import com.beerapi.beerapi.models.BreweryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BreweryMapper {
    Brewery breweryDtoToBrewery(BreweryDTO breweryDTO);
    BreweryDTO brewertyToBreweryDto(Brewery brewery);
}
