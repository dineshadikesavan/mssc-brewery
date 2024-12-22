package com.tsc.brewery.web.controller;

import com.tsc.brewery.web.model.BeerDto;
import com.tsc.brewery.web.service.BeerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable("beerId") UUID beerId) throws Exception {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> createBeer(@Valid @NotNull @RequestBody BeerDto beerDto){
       // BeerDto savedDto = beerService.createBeer(beerDto);

       // HttpHeaders headers = new HttpHeaders();
       // headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

       // return new ResponseEntity<>(headers, HttpStatus.CREATED);
        return new ResponseEntity<>(beerService.createBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) throws Exception {
        //BeerDto savedDto = beerService.updateBeer(beerId, beerDto);

        //HttpHeaders headers = new HttpHeaders();
       // headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        //return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<BeerDto> deleteBeer(@PathVariable("beerId") UUID beerId){
        BeerDto deleteDto = beerService.deleteBeer(beerId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + deleteDto.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
