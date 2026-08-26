package com.github.elenamikhailova.automation.api.data;

import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import net.datafaker.Faker;

public class UserData {
    private final Faker faker = new Faker();

    public CreateUserRequest generateRandomUser() {
        return CreateUserRequest.builder()
                .name(faker.name().firstName())
                .email("User_" + System.currentTimeMillis() + "@test.com")
                .password(faker.credentials().password())
                .title("Mrs")
                .birthDate(String.valueOf(faker.number().numberBetween(1, 29)))
                .birthMonth(String.valueOf(faker.number().numberBetween(1, 13)))
                .birthYear(String.valueOf(faker.number().numberBetween(1980, 1999)))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .address1(faker.address().streetAddress())
                .address2(faker.address().secondaryAddress())
                .country("Russia")
                .zipCode(faker.address().zipCode())
                .state(faker.address().state())
                .city(faker.address().city())
                .mobileNumber(faker.phoneNumber().cellPhone())
                .build();
    }
}
