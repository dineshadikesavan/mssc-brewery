package com.tsc.brewery.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsc.brewery.domain.Beer;
import com.tsc.brewery.repository.BeerRepository;
import com.tsc.brewery.web.model.BeerDto;
import org.hibernate.validator.internal.constraintvalidators.hv.UUIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "com.tsc.ecom", uriPort = 80)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = {"com.tsc.brewery.repository", "com.tsc.brewery.web"})
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeer() throws Exception {
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(get("/api/v1/beer/{beerId}"
                , UUID.randomUUID().toString())
                .param("isCold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer/beer-get"
                        , pathParameters(parameterWithName("beerId").description("UUID of desired to get."))
                        , queryParameters(parameterWithName("isCold").description("Is Beer Cold query param"))
                        , responseFields(fieldWithPath("id").description("Id of Beer")
                                , fieldWithPath("version").description("Version of Beer")
                                , fieldWithPath("createdDate").description("Date created")
                                , fieldWithPath("lastModifiedDate").description("Date modified")
                                , fieldWithPath("beerName").description("Name of Beer")
                                , fieldWithPath("beerStyle").description("Style of Beer")
                                , fieldWithPath("upc").description("Upc of Beer")
                                , fieldWithPath("price").description("Price of Beer")
                                , fieldWithPath("quantityOnHand").description("Quantity of Beer"))));
    }

    @Test
    void createBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        ConstraintFields fields = new ConstraintFields(BeerDto.class);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer/beer-post"
                        , requestFields(fields.withPath("id").ignored()
                                , fields.withPath("beerName").description("Name of Beer")
                                , fields.withPath("version").description("Version of Beer")
                                , fields.withPath("createdDate").description("Date created")
                                , fields.withPath("lastModifiedDate").description("Date modified")
                                , fields.withPath("beerName").description("Name of Beer")
                                , fields.withPath("beerStyle").description("Style of Beer")
                                , fields.withPath("upc").description("Upc of Beer")
                                , fields.withPath("price").description("Price of Beer")
                                , fields.withPath("quantityOnHand").description("Quantity of Beer"))));
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() {
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder().beerName("Nice Ale").beerStyle("Pale Ale").price(new BigDecimal("10.95")).upc(12234564889542L).build();
    }

    private static class ConstraintFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstraintFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}