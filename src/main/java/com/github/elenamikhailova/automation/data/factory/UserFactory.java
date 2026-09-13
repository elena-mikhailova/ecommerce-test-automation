package com.github.elenamikhailova.automation.data.factory;

import com.github.elenamikhailova.automation.api.model.request.CreateUserRequest;
import com.github.elenamikhailova.automation.data.generator.UserDataGenerator;

public class UserFactory {

    private final UserDataGenerator dataGenerator = new UserDataGenerator();

    public CreateUserRequest validUser() {
        return CreateUserRequest.builder()
                .name(dataGenerator.firstName())
                .email(dataGenerator.email())
                .password(dataGenerator.password())
                .title(dataGenerator.title())
                .birthDate(dataGenerator.birthDate())
                .birthMonth(dataGenerator.birthMonth())
                .birthYear(dataGenerator.birthYear())
                .firstName(dataGenerator.firstName())
                .lastName(dataGenerator.lastName())
                .company(dataGenerator.company())
                .address1(dataGenerator.address1())
                .address2(dataGenerator.address2())
                .country(dataGenerator.country())
                .zipCode(dataGenerator.zipCode())
                .state(dataGenerator.state())
                .city(dataGenerator.city())
                .mobileNumber(dataGenerator.mobileNumber())
                .build();
    }

    public CreateUserRequest withRandomFirstName(CreateUserRequest user) {
        return user.toBuilder()
                .firstName(dataGenerator.firstName())
                .build();
    }
}
