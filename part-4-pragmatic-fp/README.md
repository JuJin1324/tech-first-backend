# Part 4: Pragmatic FP - Hybrid Domain Entities

This document provides instructions on how to run the code for Part 4 of the tutorial.

In this part, we explore a **"Pragmatic Functional Programming"** approach where we blend the best of OOP and FP:
- **OOP**: Entities act as State Machines that mutate their own state.
- **FP**: Validation logic returns values (`Either`) instead of throwing exceptions.
- **EDA**: Methods return Events to indicate what happened, rather than just returning state.

## Key Features

-   **Hybrid State Machine**: The `Member` entity validates inputs functionally but mutates its internal state (OOP) and returns events (EDA).
-   **Side-Effect Isolation**: The Service layer orchestrates side effects (Persistence, Event Publishing) using `peek`, keeping the core flow linear and readable.
-   **Railway Oriented Programming**: Continued use of `Either` for control flow without exceptions.

## How to Run

### Method 1: Run with IntelliJ

1.  **Run the Application:**
    -   Navigate to `part-4-pragmatic-fp` > `src` > `main` > `java` > `com.techfirst.backend.pragmaticfp` and find the `PragmaticFpApplication.java` file.
    -   Click the green 'Run' icon.

### Method 2: Run with Terminal

1.  **Run the Application:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-4-pragmatic-fp:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-4-pragmatic-fp:bootRun
        ```

2.  **API Testing:**

    -   **Change Name (Success):**
        *Note: The mock repository starts with a member ID 1.*
        ```bash
        curl -X PUT http://localhost:8080/members/1/name \
          -H "Content-Type: application/json" \
          -d '{"name": "Bob"}'
        ```

    -   **Change Name (Failure - Invalid Name):**
        ```bash
        curl -X PUT http://localhost:8080/members/1/name \
          -H "Content-Type: application/json" \
          -d '{"name": "B"}'
        ```
