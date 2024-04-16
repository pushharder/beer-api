package com.beerapi.beerapi.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private int version;
    private String name;
    private String description;
    private int score;
    private String website_url;
    private String brewery_type;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
