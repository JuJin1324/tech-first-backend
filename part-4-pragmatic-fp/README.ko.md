# Part 4: 실용주의 FP - 하이브리드 도메인 엔티티

이 문서는 튜토리얼 Part 4의 코드를 실행하는 방법을 설명합니다.

이번 파트에서는 OOP와 FP의 장점을 혼합한 **"실용주의 함수형 프로그래밍(Pragmatic FP)"** 접근 방식을 탐구합니다:
- **OOP**: 엔티티는 자신의 상태를 변경하는 상태 머신(State Machine) 역할을 합니다.
- **FP**: 검증 로직은 예외를 던지는 대신 값(`Either`)을 반환합니다.
- **EDA**: 메서드는 단순히 상태를 반환하는 것이 아니라, 무슨 일이 일어났는지(Event)를 반환합니다.

## 주요 특징

-   **하이브리드 상태 머신**: `Member` 엔티티는 함수형으로 입력을 검증하지만, 내부 상태는 OOP 방식으로 변경하고, 결과로 이벤트를 반환합니다.
-   **부수효과 격리**: 서비스 계층은 `peek`를 사용하여 부수효과(저장, 이벤트 발행)를 처리하며, 핵심 로직의 흐름을 선형적으로 유지합니다.
-   **Railway Oriented Programming**: 예외 없는 제어 흐름을 위해 `Either`를 계속 사용합니다.

## 실행 방법

### 방법 1: IntelliJ로 실행

1.  **애플리케이션 실행:**
    -   `part-4-pragmatic-fp` > `src` > `main` > `java` > `com.techfirst.backend.pragmaticfp` 경로로 이동하여 `PragmaticFpApplication.java` 파일을 찾습니다.
    -   녹색 'Run' 아이콘을 클릭하여 실행합니다.

### 방법 2: 터미널로 실행

1.  **애플리케이션 실행:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-4-pragmatic-fp:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-4-pragmatic-fp:bootRun
        ```

2.  **API 테스트:**

    -   **이름 변경 (성공):**
        *참고: Mock 저장소에는 ID 1번 회원이 미리 생성되어 있습니다.*
        ```bash
        curl -X PUT http://localhost:8080/members/1/name \
          -H "Content-Type: application/json" \
          -d '{"name": "Bob"}'
        ```

    -   **이름 변경 (실패 - 잘못된 이름):**
        ```bash
        curl -X PUT http://localhost:8080/members/1/name \
          -H "Content-Type: application/json" \
          -d '{"name": "B"}'
        ```
