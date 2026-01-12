package com.asb.housingplatform.propertylisting.service;


import com.asb.housingplatform.exception.ResourceNotFoundException;
import com.asb.housingplatform.propertylisting.dto.PropertyListingCreateRequest;
import com.asb.housingplatform.propertylisting.dto.PropertyListingResponse;
import com.asb.housingplatform.propertylisting.model.PropertyListing;
import com.asb.housingplatform.propertylisting.repository.PropertyListingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyListingServiceImpl implements PropertyListingService {

    private final PropertyListingRepository propertyListingRepository;

    public PropertyListingServiceImpl(PropertyListingRepository propertyListingRepository) {
        this.propertyListingRepository = propertyListingRepository;
    }

    public PropertyListingResponse createProperty(PropertyListingCreateRequest request) {
        /*  Convierte el DTO  request (DTO entrada) -> Entity JPA (PropertyListing)
            Guardar en BD la entidad
            Convertir la Entity JPA guardada -> DTO response (DTO salida)
         */

        //Forma metodo estatico Java 21
        PropertyListing entity = PropertyListing.from(request);
        PropertyListing saved = propertyListingRepository.save(entity);
        return PropertyListingResponse.from(saved);
    }


    public PropertyListingResponse updateProperty(Long id, PropertyListingCreateRequest request) {

        PropertyListing existing = propertyListingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        existing.setTitle(request.title());
        existing.setDescription(request.description());
        existing.setPrice(request.price());
        existing.setLocation(request.location());
        // @PreUpdate se encarga de updatedAt

        PropertyListing saved = propertyListingRepository.save(existing);
        return PropertyListingResponse.from(saved);
    }


    public PropertyListingResponse getById(Long id) {
        PropertyListing entity = propertyListingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return PropertyListingResponse.from(entity);
    }


    @Override
    public Optional<PropertyListing> findById(Long id) {
        return propertyListingRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        PropertyListing entity = propertyListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property with ID " + id + " not found"));
        propertyListingRepository.delete(entity);
    }


    @Override
    @CircuitBreaker(name = "appraisalCB", fallbackMethod = "fallbackFindAll")
    public List<PropertyListingResponse> findAll() {
        // --- SIMULACRO DE FALLO EXTERNO ---
        // En un caso real, aquí llamaríamos a: appraisalClient.getMarketPrices();
        // Para probar el fallback ahora mismo, descomentar la línea de abajo:
        //if (true) { throw new RuntimeException("Simulated Appraisal Service Failure"); }

        return propertyListingRepository.findAll()
            .stream()
            .map(PropertyListingResponse::from)
            .toList();
    }

    /**
     * Se activa cuando el servicio de arriba falla.
     * Al ser un Record inmutable, creamos nuevas instancias con precio 0
     */
    public List<PropertyListingResponse> fallbackFindAll(Exception e) {
        // Logueamos el error para saber que el fallback ha funcionado
        System.err.println("CIRCUIT BREAKER LOG: Fallback activated. Reason: " + e.getMessage());
        return propertyListingRepository.findAll()
                .stream()
                .map(entity -> {
                    // Como el Record es inmutable, no podemos usar .setPrice().
                    // Creamos un nuevo objeto con el valor BigDecimal.ZERO
                    return new PropertyListingResponse(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getDescription(),
                            java.math.BigDecimal.ZERO,
                            entity.getLocation(),
                            entity.getAvailable(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()
                    );
                })
                .toList();
    }


}
