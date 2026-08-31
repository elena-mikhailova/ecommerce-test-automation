# E-commerce Test Automation

[![Tests](https://github.com/elena-mikhailova/ecommerce-test-automation/actions/workflows/tests.yml/badge.svg?branch=main)]
(https://github.com/elena-mikhailova/ecommerce-test-automation/actions/workflows/tests.yml)

Test automation project for [Automation Exercise](https://automationexercise.com/).

The project contains API and UI automated tests for basic e-commerce scenarios.

## Tech Stack

- Java 25
- Maven
- JUnit 5
- RestAssured
- Selenide
- Allure
- Datafaker
- Lombok
- SLF4J + Log4j2
- GitHub Actions

## Test Coverage

### API

- Get product list
- Search products
- Create user
- Get user by email
- Update user
- Delete user
- Login with valid credentials
- Login with invalid, empty and missing credentials
- Parameterized test scenarios

### UI

- Login with valid credentials
- Login with invalid credentials
- Parameterized product search
- Add product to cart
- Remove product from cart

UI login tests use API for user creation and cleanup.

Cart tests use an available product instead of depending on a hardcoded product name.

## Run Tests

Run all tests:

```bash
./mvnw clean test
```

Run tests in headless mode:

```bash
./mvnw clean test -Dselenide.headless=true
```

## Allure Report

Test results are generated in:

```text
target/allure-results
```

If Allure CLI is installed:

```bash
allure serve target/allure-results
```

## Configuration

Application URLs are stored in:

```text
src/test/resources/config.properties
```

## CI

GitHub Actions runs API and UI tests automatically:

- on pull requests to `main`
- on pushes to `main`

UI tests run in headless Chrome in CI.