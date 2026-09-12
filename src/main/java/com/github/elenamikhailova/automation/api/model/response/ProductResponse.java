package com.github.elenamikhailova.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductResponse(
        int id,
        String name,
        String price,
        String brand
) {
}
