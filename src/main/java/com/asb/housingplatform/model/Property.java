package com.asb.housingplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title is required")
    private String title;

    @Column(length = 1000)
    private String description;

    @Positive(message = "The price must be greater than zero")
    private BigDecimal price;

    private String location;

    private Integer bedrooms;

    private Integer bathrooms;

    private Double area;

    private String propertyType;

    private Boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

