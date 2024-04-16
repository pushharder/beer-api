package com.beerapi.beerapi.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private int version;
}
