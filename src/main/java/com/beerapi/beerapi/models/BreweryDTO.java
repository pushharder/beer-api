package com.beerapi.beerapi.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BreweryDTO {
    private UUID id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private String description;

    @Min(0)
    @Max(10)
    private int score;
    private String website_url;
    private String brewery_type;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private int version;
}
