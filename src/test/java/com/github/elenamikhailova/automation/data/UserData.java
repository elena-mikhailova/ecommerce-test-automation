package com.github.elenamikhailova.automation.data;

import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserData {
    private final Faker faker = new Faker();

    public CreateUserRequest generateRandomUser() {
        return CreateUserRequest.builder()
                .name(faker.name().firstName())
                .email("User_" + System.currentTimeMillis()
                        + "_" + faker.number().digits(5) + "@test.com")
                .password(faker.credentials().password())
                .title(faker.options().option("Mr", "Mrs", "Miss"))
                .birthDate(String.valueOf(faker.number().numberBetween(1, 29)))
                .birthMonth(String.valueOf(faker.number().numberBetween(1, 13)))
                .birthYear(String.valueOf(faker.number().numberBetween(1980, 1999)))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .address1(faker.address().streetAddress())
                .address2(faker.address().secondaryAddress())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .state(faker.address().state())
                .city(faker.address().city())
                .mobileNumber(faker.phoneNumber().cellPhone())
                .build();
    }

    public static Stream<Arguments> invalidLoginCases() {
        UserData userData = new UserData();
        CreateUserRequest user1 = userData.generateRandomUser();
        CreateUserRequest user2 = userData.generateRandomUser();
        CreateUserRequest user3 = userData.generateRandomUser();
        CreateUserRequest user4 = userData.generateRandomUser();
        CreateUserRequest user5 = userData.generateRandomUser();
        CreateUserRequest user6 = userData.generateRandomUser();

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

    public String generateFirstName() {
        return faker.name().firstName();
    }
}
