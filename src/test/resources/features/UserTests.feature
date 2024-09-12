Feature: Operations about user

  Scenario: Create a User
    Given A new user with the following details
      """
      {
        "id": 20,
        "username": "Brian",
        "firstName": "John",
        "lastName": "James",
        "email": "brian@email.com",
        "password": "12345",
        "phone": "12345",
        "userStatus": 1
      }
      """
    When I create the user
    Then I verify the user was successfully created
    When I retrieve the user by username "Brian"
    Then I verify the user information is retrieved

  Scenario: Logs user into the system
    Given A new user with the following details
      """
      {
        "id": 11,
        "username": "Daniela",
        "firstName": "Gomez",
        "lastName": "James",
        "email": "dani@email.com",
        "password": "12345",
        "phone": "6579003",
        "userStatus": 1
      }
      """
    When I create the user
    When I login the user
    Then I verify the user was successfully logged in
