package com.tsc.brewery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer {

    @Null
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, length = 36, columnDefinition = "varchar", updatable = false)
    private UUID customerId;

    @NotNull
    @Size(min = 3, max = 20)
    private String customerName;

    private String gender;

    @NotNull
    private Integer age;

}
