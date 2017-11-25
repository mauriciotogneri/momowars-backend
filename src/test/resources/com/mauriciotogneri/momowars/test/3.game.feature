Feature: Game API

	Scenario: get open games
        When I get the open games with an invalid session
        When I get the open games with a valid session

    Scenario: get game
        When I get a game with an invalid session
        When I get a game with invalid parameters
        When I get a game with a valid session and invalid id
        When I get a game with a valid session and valid id