package com.asb.housingplatform.repository;

import com.asb.housingplatform.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAll();
    Optional<Property> findById(Long id);

    Property save(Property property);
    void deleteById(Long id);

}

