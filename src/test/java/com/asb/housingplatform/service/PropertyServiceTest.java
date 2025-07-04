package com.asb.housingplatform.service;


import com.asb.housingplatform.model.Property;
import com.asb.housingplatform.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Test
    void shouldReturnPropertyWhenFound() {

        // Arrange
        Long id = 1L;
        Property property = new Property();
        property.setId(id);
        Mockito.when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        // Act
        Optional<Property> result = propertyService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        // Arrange
        Long id = 2L;
        Mockito.when(propertyRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Property> result = propertyService.findById(id);

        // Assert
        assertTrue(result.isEmpty());
    }


}
