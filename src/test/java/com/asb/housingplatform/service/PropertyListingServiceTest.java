package com.asb.housingplatform.service;


import com.asb.housingplatform.model.PropertyListing;
import com.asb.housingplatform.repository.PropertyListingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PropertyListingServiceTest {

    @Mock
    private PropertyListingRepository propertyRepository;

    @InjectMocks
    private PropertyListingServiceImpl propertyService;

    @Test
    void shouldReturnPropertyWhenFound() {

        // Arrange
        Long id = 1L;
        PropertyListing property = new PropertyListing();
        property.setId(id);
        Mockito.when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        // Act
        Optional<PropertyListing> result = propertyService.findById(id);

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
        Optional<PropertyListing> result = propertyService.findById(id);

        // Assert
        assertTrue(result.isEmpty());
    }


}
