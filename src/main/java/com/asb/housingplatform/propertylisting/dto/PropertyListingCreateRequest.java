package com.asb.housingplatform.propertylisting.dto;

import java.math.BigDecimal;

public record PropertyListingCreateRequest(
        String title,
        String description,
        BigDecimal price,
        String location
) {}