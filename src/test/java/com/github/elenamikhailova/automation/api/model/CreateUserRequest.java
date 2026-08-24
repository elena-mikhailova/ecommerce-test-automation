package com.github.elenamikhailova.automation.api.model;

import lombok.*;

@Getter
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private String title;
    private String birthDate;
    private String birthMonth;
    private String birthYear;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipCode;
    private String state;
    private String city;
    private String mobileNumber;
}
