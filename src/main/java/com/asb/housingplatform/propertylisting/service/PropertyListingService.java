package com.asb.housingplatform.propertylisting.service;

import com.asb.housingplatform.propertylisting.dto.PropertyListingCreateRequest;
import com.asb.housingplatform.propertylisting.dto.PropertyListingResponse;
import com.asb.housingplatform.propertylisting.model.PropertyListing;

import java.util.List;
import java.util.Optional;

public interface PropertyListingService {

    PropertyListingResponse createProperty(PropertyListingCreateRequest request);
    PropertyListingResponse updateProperty(Long id, PropertyListingCreateRequest request);
    PropertyListingResponse getById(Long id);

    //Spring Data JPA
    Optional<PropertyListing> findById(Long id);

    List<PropertyListingResponse> findAll();

    void deleteById(Long id);
}
