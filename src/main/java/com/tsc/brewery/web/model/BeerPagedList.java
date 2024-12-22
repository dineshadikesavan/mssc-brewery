package com.tsc.brewery.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPagedList extends PageImpl<BeerDto> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeerPagedList(@JsonProperty("content") List<BeerDto> content
                        , @JsonProperty("number") Integer number
                        , @JsonProperty("size") Integer size
                        , @JsonProperty("totalElements") Integer totalElements
                        , @JsonProperty("totalPages") Integer totalPages
                        , @JsonProperty("pageable") JsonNode pageable
                        , @JsonProperty("sort") JsonNode sort
                        , @JsonProperty("first") Boolean first
                        , @JsonProperty("last") Boolean last
                        , @JsonProperty("numberOfElements") Integer numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }

    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
