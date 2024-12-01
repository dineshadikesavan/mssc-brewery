package com.tsc.brewery.web.mappers;

import com.tsc.brewery.domain.Beer;
import com.tsc.brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
