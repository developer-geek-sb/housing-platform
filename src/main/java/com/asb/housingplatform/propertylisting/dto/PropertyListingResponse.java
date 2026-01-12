package com.asb.housingplatform.propertylisting.dto;

import com.asb.housingplatform.propertylisting.model.PropertyListing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyListingResponse(
        Long id,
        String title,
        String description,
        BigDecimal price,
        String location,
        Boolean available,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    // Fábrica estática: Entity JPA -> DTO de salida
    public static PropertyListingResponse from(PropertyListing entity) {
        return new PropertyListingResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getLocation(),
                entity.getAvailable(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    

}