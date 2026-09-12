package com.github.elenamikhailova.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDetailsResponse(
        int responseCode,
        UserDetails user
) {
}
