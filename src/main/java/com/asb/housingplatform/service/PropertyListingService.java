package com.asb.housingplatform.service;

import com.asb.housingplatform.model.PropertyListing;

import java.util.List;
import java.util.Optional;

public interface PropertyListingService {

    List<PropertyListing> findAll();
    Optional<PropertyListing> findById(Long id);
    PropertyListing save(PropertyListing property);
    void deleteById(Long id);

}
