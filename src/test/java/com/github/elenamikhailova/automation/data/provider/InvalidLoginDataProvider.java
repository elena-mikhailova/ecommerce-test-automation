package com.github.elenamikhailova.automation.data.provider;

import com.github.elenamikhailova.automation.api.model.request.CreateUserRequest;
import com.github.elenamikhailova.automation.data.factory.UserFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class InvalidLoginDataProvider {
    private InvalidLoginDataProvider() {
    }
    public static Stream<Arguments> invalidLoginCases() {
        UserFactory userFactory = new UserFactory();
        CreateUserRequest user1 = userFactory.validUser();
        CreateUserRequest user2 = userFactory.validUser();
        CreateUserRequest user3 = userFactory.validUser();
        CreateUserRequest user4 = userFactory.validUser();
        CreateUserRequest user5 = userFactory.validUser();
        CreateUserRequest user6 = userFactory.validUser();

        return Stream.of(
                Arguments.of(
                        "Empty email",
                        user1,
                        "",
                        user1.getPassword(),
                        404,
                        "User not found!"
                ),
                Arguments.of(
                        "Empty password",
                        user2,
                        user2.getEmail(),
                        "",
                        404,
                        "User not found!"
                ),
                Arguments.of(
                        "Invalid email",
                        user3,
                        "invalid_email",
                        user3.getPassword(),
                        404,
                        "User not found!"
                ),
                Arguments.of(
                        "Invalid password",
                        user4,
                        user4.getEmail(),
                        user4.getPassword() + "_inv",
                        404,
                        "User not found!"
                ),
                Arguments.of(
                        "Missing email",
                        user5,
                        null,
                        user5.getPassword(),
                        400,
                        "Bad request, email or password parameter is missing in POST request."
                ),
                Arguments.of(
                        "Missing password",
                        user6,
                        user6.getEmail(),
                        null,
                        400,
                        "Bad request, email or password parameter is missing in POST request."
                )
        );
    }
}
