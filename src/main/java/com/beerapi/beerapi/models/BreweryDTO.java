package com.beerapi.beerapi.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BreweryDTO {
    private UUID id;
    private String name;
    private String description;
    private int score;
    private String website_url;
    private String brewery_type;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private int version;
}
