# E-commerce Test Automation

[![Tests](https://github.com/elena-mikhailova/ecommerce-test-automation/actions/workflows/test-framework-ci.yml/badge.svg?branch=main)](https://github.com/elena-mikhailova/ecommerce-test-automation/actions/workflows/test-framework-ci.yml)

Test automation project for [Automation Exercise](https://automationexercise.com/).

The project contains API and UI automated tests for common e-commerce scenarios.

## Tech Stack

- Java 25
- Maven
- JUnit 5
- REST Assured
- AssertJ
- Selenide
- Jackson
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
- Login with invalid, empty, and missing credentials
- Parameterized test scenarios

### UI

- Login with valid credentials
- Login with invalid credentials
- Parameterized product search
- Product search with no results
- Add product to cart
- Remove product from cart

UI login tests use API for user creation and cleanup.

Cart tests use an available product instead of depending on a hardcoded product name.

## Test Tags

Tests are grouped using JUnit 5 tags:

- `smoke` — critical checks
- `regression` — main regression suite
- `full-regression` — full test suite
- `api` — API tests
- `ui` — UI tests

Custom annotations are used instead of direct `@Tag` declarations in test classes.

## Run Tests

Run all tests in Chrome:

```bash
./mvnw clean test
```

Run tests in headless mode:

```bash
./mvnw clean test -Dselenide.headless=true
```

## Allure Report

Generate and open the report locally:

```bash
./mvnw allure:serve
```

## Configuration

Application URLs are stored in:

```text
src/test/resources/config.properties
```

Parallel execution settings are stored in:

```text
src/test/resources/junit-platform.properties
```

Chrome is used as the default browser.

## CI

GitHub Actions runs automated checks:

- smoke tests on pull requests to `main`
- regression tests on pushes to `main`
- nightly full regression
- cross-browser UI tests in Chrome and Firefox
- environment health check before test execution
- JUnit, Surefire, UI failure, and Allure reports

UI tests run in headless mode in CI.