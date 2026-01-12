package com.asb.housingplatform.propertylisting.model;

import com.asb.housingplatform.propertylisting.dto.PropertyListingCreateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "property_listings")
public class PropertyListing {

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

    /*
        Callbacks JPA que se ejecutan automaticamente
        @PrePersist: se ejecuta antes de un INSERT
        @PreUpdate:  se ejecuta antes de un UPDATE, es decir, en PUT
    */

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /* JAVA 21: Metodo estatico de fabrica:
            es un metodo dentro de la entidad que recibe un DTO (u otros argumentos)
            crea la entidad correctamente
            inicializa valores por defecto
            evita duplicar lógica en el servicio
     */

    // Fábrica estatica:  DTO de entrada  -> Entity JPA
    public static PropertyListing from(PropertyListingCreateRequest dto){
        return PropertyListing.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .location(dto.location())
                .available(true)         // valor por defecto
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}

