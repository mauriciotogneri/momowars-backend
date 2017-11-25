Feature: Session API

    Scenario: session operations
        When I login with no parameters
        When I login with an invalid email
        When I login with an invalid password
        When I login with valid credentials