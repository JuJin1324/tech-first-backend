# Part 2: REST and SOAP Adapter

This document provides instructions on how to run the code for Part 2 of the tutorial.

## How to Run

### Method 1: Run with IntelliJ
1.  **Run the Application:**
    -   Navigate to `part-2-rest-and-soap-adapter` > `src` > `main` > `java` > `com.techfirst.backend.restsoap` and find the `HexagonalApplication.java` file.
    -   Click the green 'Run' icon next to the `main` method to execute the application.

2.  **Verify Results:**
    -   **REST API:** Access `http://localhost:8080/api/customers/1` in your web browser to see the JSON response.
    -   **SOAP WSDL:** Access `http://localhost:8080/ws/customers.wsdl` to see the WSDL definition.

### Method 2: Run with Terminal
1.  **Run the Application:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-2-rest-and-soap-adapter:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-2-rest-and-soap-adapter:bootRun
        ```

2.  **Verify Results:**
    -   **REST API:**
        ```bash
        curl http://localhost:8080/api/customers/1
        ```
    -   **SOAP WSDL:**
        Open `http://localhost:8080/ws/customers.wsdl` in your browser.