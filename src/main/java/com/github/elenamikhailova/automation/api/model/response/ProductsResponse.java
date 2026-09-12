package com.github.elenamikhailova.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductsResponse(
        int responseCode,
        List<ProductResponse> products
) {
}
