package com.asb.housingplatform.repository;

import com.asb.housingplatform.model.PropertyListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyListingRepository extends JpaRepository<PropertyListing, Long> {

    List<PropertyListing> findAll();
    Optional<PropertyListing> findById(Long id);

    PropertyListing save(PropertyListing property);
    void deleteById(Long id);

}

