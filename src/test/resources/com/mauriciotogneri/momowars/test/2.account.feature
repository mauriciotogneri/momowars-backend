Feature: Account API

	Scenario: create account
        When I create a new account with missing data
        When I create a new account with an existing email
        When I create a new account with valid data

    Scenario: get account
        When I get the account with an invalid session
        When I get the account with a valid session
        
    Scenario: update account
        When I update the account with an invalid session
        When I update the account with invalid parameters
        When I update the account with valid parameters