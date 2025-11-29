package com.asb.housingplatform.service;


import com.asb.housingplatform.exception.ResourceNotFoundException;
import com.asb.housingplatform.model.PropertyListing;
import com.asb.housingplatform.repository.PropertyListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyListingServiceImpl implements PropertyListingService {

    private final PropertyListingRepository propertyListingRepository;

    public PropertyListingServiceImpl(PropertyListingRepository propertyListingRepository) {
        this.propertyListingRepository = propertyListingRepository;
    }

    @Override
    public List<PropertyListing> findAll() {
        return propertyListingRepository.findAll();
    }

    @Override
    public Optional<PropertyListing> findById(Long id) {
        return propertyListingRepository.findById(id);
    }

    @Override
    public PropertyListing save(PropertyListing property) {
        return propertyListingRepository.save(property);
    }

    @Override
    public void deleteById(Long id) {
        Optional<PropertyListing> property = propertyListingRepository.findById(id);
        if (property.isEmpty()) {
            throw new ResourceNotFoundException("Property with ID " + id + " not found");
        }
        propertyListingRepository.deleteById(id);
    }
}
