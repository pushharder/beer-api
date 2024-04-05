package com.beerapi.beerapi.bootstrap;

import com.beerapi.beerapi.models.Beer;
import com.beerapi.beerapi.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {
    private final BeerService beerService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Data bootstrapping started");

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .name("Singa")
                .description("don't drink alcohol")
                .price(4.99)
                .score(5)
                .firstBrewed(LocalDateTime.now())
                .imageUrl("singa url")
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .name("Heineken")
                .description("crap")
                .price(3.99)
                .score(2)
                .firstBrewed(LocalDateTime.now())
                .imageUrl("heineken url")
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .name("Carlsberg")
                .description("super crap")
                .price(2.99)
                .score(3)
                .firstBrewed(LocalDateTime.now())
                .imageUrl("carlsberg url")
                .build();

        beerService.addBeer(beer1);
        beerService.addBeer(beer2);
        beerService.addBeer(beer3);

        log.info("Data bootstrapping finished");

    }
}
