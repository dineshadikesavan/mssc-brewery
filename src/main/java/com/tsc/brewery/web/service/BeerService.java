package com.tsc.brewery.web.service;

import com.tsc.brewery.web.model.BeerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(@NotNull UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDto createBeer(@Valid @NotNull BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, @Valid BeerDto beerDto) throws ChangeSetPersister.NotFoundException;

    BeerDto deleteBeer(UUID beerId);
}
