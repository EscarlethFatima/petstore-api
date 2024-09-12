Feature: Operations about pet

  Scenario: Add a new pet to the store
    Given A pet with the following details
      """
      {
        "id": 100,
        "name": "doggie",
        "category": {
          "id": 1,
          "name": "Dogs"
        },
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """
    When I add the new pet to the store
    Then The pet is created successfully
    When I retrieve the pet by id 100
    Then The pet name is "doggie"

  Scenario: Update a pet's details
    Given A pet with the following details
      """
      {
        "id": 101,
        "name": "kitty",
        "category": {
          "id": 2,
          "name": "Cats"
        },
        "photoUrls": [
          "photo1"
        ],
        "tags": [
          {
            "id": 1,
            "name": "cute"
          }
        ],
        "status": "available"
      }
      """
    When I add the new pet to the store
    And I update the pet with the following details
      """
      {
        "id": 101,
        "name": "kitty",
        "category": {
          "id": 2,
          "name": "Cats"
        },
        "photoUrls": [
          "photo2"
        ],
        "tags": [
          {
            "id": 1,
            "name": "adorable"
          }
        ],
        "status": "adopted"
      }
      """
    Then The pet is updated successfully
    When I retrieve the pet by id 101
    Then The pet name is "kitty"
    And The pet status is "adopted"
    And The pet tag name is "adorable"

  Scenario: Delete a pet and verify deletion
    Given A pet with the following details
      """
      {
        "id": 102,
        "name": "bunny",
        "category": {
          "id": 3,
          "name": "Rabbits"
        },
        "photoUrls": [
          "photo3"
        ],
        "tags": [
          {
            "id": 2,
            "name": "fluffy"
          }
        ],
        "status": "available"
      }
      """
    When I add the new pet to the store
    And I delete the pet with id 102
    Then The pet is deleted successfully
    When I retrieve the pet by id 102
    Then The response status code is 404
