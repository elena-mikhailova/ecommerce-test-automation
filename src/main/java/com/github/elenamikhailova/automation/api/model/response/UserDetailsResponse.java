package com.github.elenamikhailova.automation.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsResponse {
    private int responseCode;
    private UserDetails user;
}
