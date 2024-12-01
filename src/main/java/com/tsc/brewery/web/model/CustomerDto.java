package com.tsc.brewery.web.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    @Null
    private UUID customerId;

    @NotNull
    @Size(min = 3, max = 20)
    private String customerName;

    private String gender;

    @NotNull
    @Min(18)
    @Max(90)
    private Integer age;
}
