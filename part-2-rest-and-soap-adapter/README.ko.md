# Part 2: REST와 SOAP 어댑터 (REST and SOAP Adapter)

이 문서는 튜토리얼 2부에 해당하는 코드를 실행하는 방법을 안내합니다.

## 실행 방법

### 방법 1: 인텔리제이(IntelliJ)로 실행하기

1.  **애플리케이션 실행:**
    -   `part-2-rest-and-soap-adapter` > `src` > `main` > `java` > `com.techfirst.backend.restsoap` 경로로 이동하여 `HexagonalApplication.java` 파일을 찾습니다.
    -   `main` 메소드 옆에 있는 녹색 'Run' 아이콘을 클릭하여 애플리케이션을 실행합니다.

2.  **결과 확인:**
    -   **REST API:** 웹 브라우저에서 `http://localhost:8080/api/customers/1` 로 접속하여 JSON 결과가 잘 나오는지 확인합니다.
    -   **SOAP WSDL:** 웹 브라우저에서 `http://localhost:8080/ws/customers.wsdl` 로 접속하여 WSDL 명세가 나오는지 확인합니다.

### 방법 2: 터미널(명령줄)로 실행하기

1.  **애플리케이션 실행:**
    -   **Linux / macOS:**
        ```bash
        ./gradlew :part-2-rest-and-soap-adapter:bootRun
        ```
    -   **Windows:**
        ```cmd
        gradlew.bat :part-2-rest-and-soap-adapter:bootRun
        ```

2.  **결과 확인:**
    -   **REST API:**
        ```bash
        curl http://localhost:8080/api/customers/1
        ```
    -   **SOAP WSDL:**
        웹 브라우저에서 `http://localhost:8080/ws/customers.wsdl` 을 확인합니다.