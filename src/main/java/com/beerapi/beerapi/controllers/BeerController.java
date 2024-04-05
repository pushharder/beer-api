package com.beerapi.beerapi.controllers;

import com.beerapi.beerapi.models.Beer;
import com.beerapi.beerapi.services.BeerService;

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
@RequestMapping("/api/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public List<Beer> getBeers() {
        return beerService.getBeers();
    }

    @GetMapping("{id}")
    public Beer getBeerById(@PathVariable("id") UUID id) {
        return beerService.getBeerById(id);
    }

    @PostMapping
    public ResponseEntity addBeer(@RequestBody Beer beer) {
        var savedBeer = beerService.addBeer(beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/beer" + savedBeer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateBeer(@PathVariable("id") UUID id, @RequestBody Beer beer) {
        beerService.editBeer(id, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
