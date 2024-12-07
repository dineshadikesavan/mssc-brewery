package com.tsc.brewery.web.service;

import com.tsc.brewery.web.model.BeerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(@NotNull UUID beerId);

    BeerDto createBeer(@Valid @NotNull BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, @Valid BeerDto beerDto);

    BeerDto deleteBeer(UUID beerId);
}
