# Part 1: Hexagonal Architecture

This document provides instructions on how to run the code for Part 1 of the tutorial.

## How to Run

### Method 1: Run with IntelliJ
1.  **Run the Application:**
    -   Navigate to `part-1-hexagonal-architecture` > `src` > `main` > `java` > `com.techfirst.backend.hexagonal` and find the `HexagonalApplication.java` file.
    -   Click the green 'Run' icon next to the `main` method to execute the application.

2.  **Verify Results:**
    -   **REST API:** Access `http://localhost:8080/api/customers/1` in your web browser to see the JSON response.
    -   **SOAP WSDL:** Access `http://localhost:8080/ws/customers.wsdl` to see the WSDL definition.

### Method 2: Run with Terminal
1.  **Run the Application:**
    ```bash
    ./gradlew :part-1-hexagonal-architecture:bootRun
    ```

2.  **Verify Results:**
    -   **REST API:**
        ```bash
        curl http://localhost:8080/api/customers/1
        ```
    -   **SOAP WSDL:**
        Open `http://localhost:8080/ws/customers.wsdl` in your browser.
