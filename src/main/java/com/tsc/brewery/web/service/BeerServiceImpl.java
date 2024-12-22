package com.tsc.brewery.web.service;

import com.tsc.brewery.domain.Beer;
import com.tsc.brewery.repository.BeerRepository;
import com.tsc.brewery.web.mappers.BeerMapper;
import com.tsc.brewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("beerService")
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService  {

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    public BeerDto getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

   public BeerDto createBeer(BeerDto beerDto) {
       return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException {
        Beer beer = beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

   public BeerDto deleteBeer(UUID beerId) {
        return BeerDto.builder().id(beerId)
                .beerName(null)
                .beerStyle(null)
                .upc(null)
                .build();
    }


}
