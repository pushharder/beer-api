package com.beerapi.beerapi.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Beer {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int score;
    private LocalDateTime firstBrewed;
    private String imageUrl;
}
