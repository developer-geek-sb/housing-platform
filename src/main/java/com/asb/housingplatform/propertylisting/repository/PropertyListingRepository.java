package com.asb.housingplatform.propertylisting.repository;

import com.asb.housingplatform.propertylisting.model.PropertyListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyListingRepository extends JpaRepository<PropertyListing, Long> {

    PropertyListing save(PropertyListing property);

    List<PropertyListing> findAll();

    Optional<PropertyListing> findById(Long id);

    void deleteById(Long id);

}

