package com.tsc.brewery.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;
    private Integer version;
    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min = 3, max = 50)
    private String beerName;

    @NotBlank
    private String beerStyle;

    @Positive
    private Long upc;
    private BigDecimal price;
    private Integer quantityOnHand;
}
