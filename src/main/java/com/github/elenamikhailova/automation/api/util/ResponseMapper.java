package com.github.elenamikhailova.automation.api.util;

import io.restassured.response.Response;

public final class ResponseMapper {

    private ResponseMapper() {

    }

    public static <T> T map(Response response, Class<T> responseClass) {
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            throw new IllegalStateException("Response deserialization failed for " + responseClass.getSimpleName(),
                    e);
        }
    }
}
