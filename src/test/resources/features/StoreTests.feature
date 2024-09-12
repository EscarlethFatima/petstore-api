Feature: Operations about store

  Scenario: Place and verify a new order in the store
    Given A new order is placed with the following details
      """
      {
        "id": 10,
        "petId": 198772,
        "quantity": 7,
        "shipDate": "2024-09-12T01:21:48.197Z",
        "status": "approved",
        "complete": true
      }
      """
    When I submit the order
    Then I verify the order is successfully created with ID 10
    When I retrieve the order by ID 10
    Then The retrieved order details should match the submitted order

  Scenario: Delete a purchase in the store
    Given A new order is placed with the following details
      """
      {
        "id": 10,
        "petId": 198772,
        "quantity": 7,
        "shipDate": "2024-09-12T01:21:48.197Z",
        "status": "approved",
        "complete": true
      }
      """
    When I submit the order
    Then I verify the order is successfully created with ID 10
    And I delete the purchase with id 10
    Then The response status code should be 200
    When I retrieve the order by ID 10
    Then The response status code should be 404
