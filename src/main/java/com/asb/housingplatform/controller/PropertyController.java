package com.asb.housingplatform.controller;

import com.asb.housingplatform.model.Property;
import com.asb.housingplatform.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Operation(summary = "List all properties", description = "Returns a list of all registered properties")
    @ApiResponse(responseCode = "200", description = "Property list retrieved successfully")
    @GetMapping("/all")
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @Operation(summary = "Retrieve a property by ID",
            description = "Returns the details of a registered property using its unique identifier")
    @ApiResponse(responseCode = "200", description = "Property retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(
           summary = "Create a new property",
          description = "Registers a new property and returns the created resource with its location"
    )
    @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Property created successfully"),
           @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property saved = propertyService.save(property);
        URI location = URI.create("/api/properties/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }



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
            @Valid @RequestBody Property property,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        return propertyService.findById(id)
                .map(existing -> {
                    existing.setTitle(property.getTitle());
                    existing.setPrice(property.getPrice());
                    existing.setLocation(property.getLocation());
                    existing.setArea(property.getArea());
                    Property updated = propertyService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(
      summary = "Delete a property by ID",
      description = "Deletes a registered property using its unique identifier. Returns no content if successful."
    )
    @ApiResponses(value = {
       @ApiResponse(responseCode = "204", description = "Property deleted successfully"),
       @ApiResponse(responseCode = "404", description = "Property not found")
     })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
