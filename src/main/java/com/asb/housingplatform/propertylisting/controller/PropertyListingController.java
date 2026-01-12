package com.asb.housingplatform.propertylisting.controller;

import com.asb.housingplatform.propertylisting.dto.PropertyListingCreateRequest;
import com.asb.housingplatform.propertylisting.dto.PropertyListingResponse;
import com.asb.housingplatform.propertylisting.model.PropertyListing;
import com.asb.housingplatform.propertylisting.service.PropertyListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyListingController {
    private final PropertyListingService propertyListingService;

    public PropertyListingController(PropertyListingService propertyListingService) {
        this.propertyListingService = propertyListingService;
    }

    /*
        @RequestBody → indica que Spring tiene que convertir el JSON al DTO.
        El controlador delega en el servicio para crear la entidad.
        Un controlador HTTP (ResponseEntity) debe decidir qué código de estado devolver (200, 201, 404, 400…)
        enviar un body (DTO) y opcionalmente headers
     */
    @Operation(
          summary = "Create a new property",
          description = "Registers a new property and returns the created resource with its location"
    )
    @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Property created successfully"),
           @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<PropertyListingResponse> createProperty(
            @RequestBody PropertyListingCreateRequest request) {
        PropertyListingResponse response = propertyListingService.createProperty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
        @Override
        public PropertyListingResponse updateProperty(Long id, PropertyListingCreateRequest request) {
            1. Buscar la entidad existente
            2. Actualizar campos del request
            3. Actualizar updatedAt
            4. Guardar
            5. Convertir a DTO de salida
        }
    */
    //TODO Cambiar el BindingResult por alguna de estas opciones
    /*
        -  @ControllerAdvice
        -  @ExceptionHandler
        -  respuestas JSON estructuradas
     */
    @Operation(
         summary = "Update an existing property",
         description = "Updates the details of a registered property by its ID. Returns the updated property or validation errors."
    )
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Property updated successfully"),
          @ApiResponse(responseCode = "400", description = "Invalid input data"),
          @ApiResponse(responseCode = "404", description = "Property not found")
     })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(
            @PathVariable Long id,
            @Valid @RequestBody PropertyListingCreateRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        PropertyListingResponse response = propertyListingService.updateProperty(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Retrieve a property by ID",
            description = "Returns the details of a registered property using its unique identifier")
    @ApiResponse(responseCode = "200", description = "Property retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Property not found")
    @GetMapping("/{id}")
    public ResponseEntity<PropertyListingResponse> getById(@PathVariable Long id) {
        PropertyListingResponse response = propertyListingService.getById(id);
        return ResponseEntity.ok(response);
    }


    @Operation(
      summary = "Delete a property by ID",
      description = "Deletes a registered property using its unique identifier. Returns no content if successful."
    )
    @ApiResponse(responseCode = "204", description = "Property deleted")
    @ApiResponse(responseCode = "404", description = "Property not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyListingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "List all properties",
            description = "Returns a list of all registered properties"
    )
    @ApiResponse(responseCode = "200", description = "Property list retrieved successfully")
    @GetMapping
    public ResponseEntity<List<PropertyListingResponse>> getAllProperties() {
        return ResponseEntity.ok(propertyListingService.findAll());
    }


}
