# Part 3: Honest Factory & Railway Oriented Programming

This document provides instructions on how to run the code for Part 3 of the tutorial.

In this part, we explore the **"Honest Constructor"** pattern and **Railway Oriented Programming (ROP)** to write safe, predictable code without relying on exceptions for control flow.

## Key Features

-   **Pure Domain Model**: Implemented pure Java domain entities without dependencies on external frameworks like JPA.
-   **Honest Constructor**: Uses static factory methods and Vavr `Either` to guarantee object validity upon creation or return an explicit error.
-   **Railway Oriented Programming**: Controls the success/failure flow in service logic using `flatMap` chaining instead of `try-catch` blocks.
-   **Mock Repository**: Uses an In-Memory Mock Repository that can run independently without an external database.

## How to Run

### Method 1: Run with IntelliJ

1.  **Run the Application:**
    -   Navigate to `part-3-honest-factory` > `src` > `main` > `java` > `com.techfirst.backend.honestfactory` and find the `HonestFactoryApplication.java` file.
    -   Click the green 'Run' icon next to the `main` method to execute the application.

2.  **Verify Results:**
    -   Test the API using a terminal or an API client (like Postman).

### Method 2: Run with Terminal

1.  **Run the Application:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-3-honest-factory:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-3-honest-factory:bootRun
        ```

2.  **API Testing:**

    -   **Register Member (Success):**
        ```bash
        curl -X POST http://localhost:8080/members \
          -H "Content-Type: application/json" \
          -d '{"name": "Jujin", "age": 30}'
        ```

    -   **Register Member (Failure - Invalid Name):**
        ```bash
        curl -X POST http://localhost:8080/members \
          -H "Content-Type: application/json" \
          -d '{"name": "", "age": 30}'
        ```

    -   **Get Member:**
        ```bash
        # IDs are assigned sequentially starting from 1.
        curl http://localhost:8080/members/1
        ```
