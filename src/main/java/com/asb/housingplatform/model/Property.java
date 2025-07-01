package com.asb.housingplatform.model;

import jakarta.persistence.*;
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

    private String title;

    @Column(length = 1000)
    private String description;

    private BigDecimal price;

    private String location;

    private Integer bedrooms;

    private Integer bathrooms;

    private Double area; // en metros cuadrados

    private String propertyType; // Ej: "Apartamento", "Casa", "Local"

    private Boolean available;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

