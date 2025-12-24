# Part 3: Honest Factory & Railway Oriented Programming

이 문서는 튜토리얼 3부에 해당하는 코드를 실행하는 방법을 안내합니다.

이번 파트에서는 **"거짓말하지 않는 생성자(Honest Constructor)"** 패턴과 **Railway Oriented Programming(ROP)** 기법을 사용하여, 예외(Exception) 없이 안전하고 예측 가능한 코드를 작성하는 방법을 다룹니다.

## 주요 특징

- **Pure Domain Model**: JPA 등 외부 프레임워크에 의존하지 않는 순수 자바 도메인 엔티티를 구현했습니다.
- **Honest Constructor**: 정적 팩토리 메서드와 Vavr `Either`를 사용하여, 객체 생성 시점부터 유효성을 보장하거나 명시적인 에러를 반환합니다.
- **Railway Oriented Programming**: 서비스 로직에서 `try-catch` 대신 `flatMap` 체이닝을 사용하여 성공/실패 흐름을 물 흐르듯 제어합니다.
- **Mock Repository**: 외부 DB 없이 독립적으로 실행 가능한 In-Memory Mock Repository를 사용합니다.

## 실행 방법

### 방법 1: 인텔리제이(IntelliJ)로 실행하기

1.  **애플리케이션 실행:**
    -   `part-3-honest-factory` > `src` > `main` > `java` > `com.techfirst.backend.honestfactory` 경로로 이동하여 `HonestFactoryApplication.java` 파일을 찾습니다.
    -   `main` 메소드 옆에 있는 녹색 'Run' 아이콘을 클릭하여 애플리케이션을 실행합니다.

2.  **결과 확인:**
    -   터미널이나 API 클라이언트(Postman 등)를 사용하여 API를 테스트합니다.

### 방법 2: 터미널(명령줄)로 실행하기

1.  **애플리케이션 실행:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-3-honest-factory:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-3-honest-factory:bootRun
        ```

2.  **API 테스트:**

    -   **회원 등록 (성공):**
        ```bash
        curl -X POST http://localhost:8080/members \
          -H "Content-Type: application/json" \
          -d '{"name": "Jujin", "age": 30}'
        ```

    -   **회원 등록 (실패 - 이름 누락):**
        ```bash
        curl -X POST http://localhost:8080/members \
          -H "Content-Type: application/json" \
          -d '{"name": "", "age": 30}'
        ```

    -   **회원 조회:**
        ```bash
        # ID는 1부터 순차적으로 부여됩니다.
        curl http://localhost:8080/members/1
        ```
