package com.tsc.brewery.web.service;

import com.tsc.brewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    public BeerDto getBeerById(UUID beerId);

    public BeerDto createBeer(BeerDto beerDto);
}
