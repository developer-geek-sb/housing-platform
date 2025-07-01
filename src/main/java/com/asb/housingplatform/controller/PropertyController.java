package com.asb.housingplatform.controller;

import com.asb.housingplatform.model.Property;
import com.asb.housingplatform.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property saved = propertyService.save(property);
        URI location = URI.create("/api/properties/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return propertyService.findById(id)
                .map(existing -> {
                    // Actualizar campos
                    existing.setTitle(property.getTitle());
                    existing.setPrice(property.getPrice());
                    existing.setLocation(property.getLocation());
                    // ... otros campos si los tienes

                    Property updated = propertyService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }*/

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
                    Property updated = propertyService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
