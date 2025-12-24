# Tech-First Backend

[View English Document (영문으로 보기)](README.md)

이 저장소는 "Tech-First Backend" 튜토리얼 시리즈를 위한 소스 코드를 관리합니다. 이 가이드는 시리즈의 각 파트 코드를 당신의 컴퓨터에서 실행하는 데 도움을 줄 것입니다.

## 🛠️ 사전 요구사항 (필수 환경)

프로젝트를 실행하기 전에 다음 환경이 갖추어져 있는지 확인해 주세요.

-   **Java Development Kit (JDK):** **Java 21** 버전이 필요합니다. (Spring Boot 3.x 구동을 위해 최소 Java 17 이상 필수)
-   **Gradle:** **8.9** 버전 (프로젝트에 Gradle Wrapper `./gradlew`가 포함되어 있어 별도 설치는 필요 없지만, 래퍼 실행을 위해 호환되는 JDK가 `JAVA_HOME`에 설정되어 있어야 합니다).

## 프로젝트 구성

이 프로젝트는 다음과 같은 주요 디렉터리로 구성되어 있습니다.

```
tech-first-backend/
├── part-2-rest-and-soap-adapter/  # 두 번째 튜토리얼 파트의 코드 (REST와 SOAP 어댑터)
├── part-3-honest-factory/       # 세 번째 튜토리얼 파트의 코드 (Honest Factory 및 Railway Oriented Programming)
└── docs/                          # 기술 문서 및 다이어그램
```

## 코드 실행 방법

각 파트의 코드를 실행하는 상세한 방법은 각 모듈 디렉터리 안의 `README.ko.md` 파일을 참고해 주세요.

*   **[Part 2: REST와 SOAP 어댑터 (REST and SOAP Adapter)](./part-2-rest-and-soap-adapter/README.ko.md)**
*   **[Part 3: Honest Factory & Railway Oriented Programming](./part-3-honest-factory/README.ko.md)**