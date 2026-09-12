package com.github.elenamikhailova.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDetails(
        String email,
        @JsonProperty("first_name")
        String firstName
) {
}
