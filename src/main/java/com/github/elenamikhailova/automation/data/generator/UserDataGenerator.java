package com.github.elenamikhailova.automation.data.generator;

import net.datafaker.Faker;

public class UserDataGenerator {
    private final Faker faker = new Faker();


    public String firstName() {
        return faker.name().firstName();
    }

    public String email() {
        return "User_"
                + System.currentTimeMillis()
                + "_"
                + faker.number().digits(5)
                + "@test.com";
    }

    public String password() {
        return faker.credentials().password();
    }

    public String title() {
        return faker.options().option("Mr", "Mrs", "Miss");
    }

    public String birthDate() {
        return String.valueOf(faker.number().numberBetween(1, 29));
    }

    public String birthMonth() {
        return String.valueOf(faker.number().numberBetween(1, 13));
    }

    public String birthYear() {
        return String.valueOf(faker.number().numberBetween(1980, 1999));
    }

    public String lastName() {
        return faker.name().lastName();
    }

    public String company() {
        return faker.company().name();
    }

    public String address1() {
        return faker.address().streetAddress();
    }

    public String address2() {
        return faker.address().secondaryAddress();
    }

    public String country() {
        return faker.address().country();
    }

    public String zipCode() {
        return faker.address().zipCode();
    }

    public String state() {
        return faker.address().state();
    }

    public String city() {
        return faker.address().city();
    }

    public String mobileNumber() {
        return faker.phoneNumber().cellPhone();
    }

}
