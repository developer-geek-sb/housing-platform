package com.asb.housingplatform.integration;


import com.asb.housingplatform.model.Property;
import com.asb.housingplatform.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyRepository propertyRepository;

    private Property savedProperty;

    @BeforeAll
    void setup() {
        Property property = new Property();
        property.setTitle("Casa en Madrid");
        property.setPrice(BigDecimal.valueOf(2500000));
        property.setLocation("Madrid");
        property.setArea(Double.valueOf(120));
        savedProperty = propertyRepository.save(property);
    }

    @Test
    void shouldReturnPropertyById() throws Exception {
        mockMvc.perform(get("/api/properties/" + savedProperty.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Casa en Madrid"))
                .andExpect(jsonPath("$.price").value(2500000));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/api/properties/9999"))
                .andExpect(status().isNotFound());
    }


}
