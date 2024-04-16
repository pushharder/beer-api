package com.beerapi.beerapi.controllers;

import com.beerapi.beerapi.models.BreweryDTO;
import com.beerapi.beerapi.services.BreweryService;

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
@RequestMapping("/api/brewery")
public class BreweryController {
    private final BreweryService breweryService;

    @GetMapping
    public List<BreweryDTO> getBreweries() {
        return breweryService.getBreweries();
    }

    @GetMapping("{id}")
    public BreweryDTO getBreweryById(@PathVariable("id") UUID id) {
        return breweryService.getBreweryById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addBrewery(@RequestBody BreweryDTO brewery) {
        var savedBrewery = breweryService.addBrewery(brewery);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/brewery/" + savedBrewery.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editBrewery(@PathVariable("id") UUID id, @RequestBody BreweryDTO brewery) {
        breweryService.editBrewery(id, brewery);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrewery(@PathVariable("id") UUID id) {
        breweryService.deleteBrewery(id);
    }
}
