package com.asb.housingplatform.service;


import com.asb.housingplatform.exception.ResourceNotFoundException;
import com.asb.housingplatform.model.Property;
import com.asb.housingplatform.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements  PropertyService{

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> findById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isEmpty()) {
            throw new ResourceNotFoundException("Property with ID " + id + " not found");
        }
        propertyRepository.deleteById(id);
    }
}
