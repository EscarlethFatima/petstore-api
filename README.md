# Petstore API Testing

## Overview

This project includes automated API tests and performance testing for the Petstore API. The API tests are implemented using Java, Cucumber, and Rest Assured, while performance testing is performed using k6. This `README` provides instructions on how to set up, run, and generate reports for both API tests and performance tests.

## Prerequisites

- **Java**: Ensure you have JDK 8 or higher installed.
- **Gradle**: Ensure you have Gradle installed. You can install it from [Gradle's official website](https://gradle.org/install/).
- **k6**: Ensure you have k6 installed. You can install it from [k6's official website](https://k6.io/docs/getting-started/installation/).

## API Tests
### Setup

1. **Clone the repository**:

   ```bash
   git clone [https://github.com/your-repo/petstore-api-tests.git](https://github.com/EscarlethFatima/petstore-api.git)
   cd petstore-api-tests

2. **Install dependencies**:
   ```bash
    ./gradlew build

3. **Execute the tests**:
   ```bash
    ./gradlew test
   
4. **View Report**:  
   After running the tests, the reports will be available in the ./testReport/

## Performance Tests

1. **Execute the tests**:
   You can specify the script to run that are under the `performanceTesting/k6/scripts` path. Examples:
   Examples:
   ```bash
    k6 run get_pet_by_status.js  
    k6 run get_pet_by_inventory.js  
    k6 run get_user_login.js  
   
2.  **View HTML Report**:
    After executing the tests, the HTML report will be stored in the performanceTesting/k6/reports directory. Open the HTML report in a browser to view the detailed performance results.
