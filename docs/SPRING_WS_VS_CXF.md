# [SOAP] Spring Web Services vs Apache CXF: 무엇을 선택해야 할까?

Spring Boot 환경에서 SOAP 웹 서비스를 구현해야 할 때, 가장 많이 언급되는 두 가지 선택지는 **Spring Web Services (Spring-WS)**와 **Apache CXF**입니다.

특히 외부에서 **WSDL(Web Services Description Language)** 파일을 제공받아 연동해야 하는 경우(Contract-First), 두 프레임워크 모두 훌륭한 해결책이 될 수 있습니다. 하지만 설계 철학과 사용 방식에는 분명한 차이가 있습니다.

## 1. 외부 WSDL로 Spring Web Services 구현이 가능한가?

**결론부터 말하자면, "매우 적합합니다."**

Spring Web Services는 태생부터 **Contract-First (계약 우선)** 개발 방식을 지향합니다. 즉, 자바 코드를 먼저 짜서 WSDL을 만드는 것이 아니라, **이미 존재하는 WSDL이나 XSD(XML Schema)를 기반으로 자바 코드를 구현**하는 데 최적화되어 있습니다. 따라서 외부에서 WSDL을 전달받은 상황이라면 Spring-WS는 가장 자연스러운 선택지 중 하나입니다.

---

## 2. 핵심 비교 요약

| 특징 | Spring Web Services (Spring-WS) | Apache CXF |
| :--- | :--- | :--- |
| **설계 철학** | **Contract-First Only**<br>(WSDL/XSD가 무조건 먼저 있어야 함) | **Contract-First & Contract-Last**<br>(자바 코드 먼저 작성 가능) 모두 지원 |
| **표준 준수** | **JAX-WS 표준 미사용**<br>(Spring 자체 어노테이션 사용) | **JAX-WS 표준 구현체**<br>(`@WebService` 등 표준 API 사용) |
| **동작 방식** | **Document-Driven**<br>(XML 문서를 주고받는 처리에 집중) | **RPC-Style**<br>(마치 자바 메소드를 호출하듯 동작)에 강점 |
| **Spring 통합** | **Spring Native**<br>(Spring MVC와 유사, DI/AOP 완벽 활용) | Spring과 통합 가능하나,<br>별도의 독립 프레임워크 성격이 강함 |
| **유연성** | WSDL 변경에 유연하며,<br>설정이 간결함 (Spring Boot) | WS-* (Security, ReliableMessaging 등)<br>복잡한 스펙을 아주 상세하게 지원 |

---

## 3. Apache CXF (The Standard Powerhouse)

Apache CXF는 오랫동안 사용되어 온 강력한 웹 서비스 프레임워크로, JAX-WS(Java API for XML Web Services) 표준을 따릅니다.

### 특징
*   **JAX-WS 표준 준수:** `@WebService`, `@WebMethod` 등 자바 표준 어노테이션을 사용합니다.
*   **WSDL2Java:** WSDL을 입력하면 **SEI(Service Endpoint Interface)** 자바 인터페이스를 자동 생성해줍니다. 개발자는 이 인터페이스를 `implements`하여 비즈니스 로직만 채우면 됩니다.
*   **다양한 프로토콜 지원:** SOAP뿐만 아니라 REST(JAX-RS)도 하나의 서비스에서 동시에 지원할 수 있습니다.

### 장점
*   기존 레거시 시스템(JAX-WS 기반)을 마이그레이션할 때 코드 수정이 적습니다.
*   메소드 호출 방식(RPC)이 익숙한 개발자에게 직관적입니다.
*   매우 복잡하고 구체적인 WS-* 보안 설정이 필요한 경우 강력한 기능을 제공합니다.

### 단점
*   Spring Boot와 연동 시 별도의 설정(Servlet 등록 등)이 필요하여 다소 무거울 수 있습니다.
*   WSDL과 자바 코드(인터페이스)가 강하게 결합되는 경향이 있습니다.

---

## 4. Spring Web Services (The Spring Way)

Spring 생태계에 익숙한 개발자에게 가장 편안한 방식을 제공합니다. 표준 자바 API(JAX-WS)를 따르지 않고, Spring만의 방식으로 SOAP을 재해석했습니다.

### 특징
*   **Spring Native:** Spring MVC의 `@Controller`와 유사한 `@Endpoint`를 사용하며, `@PayloadRoot`를 통해 XML 메시지를 라우팅합니다.
*   **Document-Driven:** "메소드 호출"보다는 **"도착한 XML 문서를 어떻게 처리할 것인가"**에 집중합니다.
*   **느슨한 결합:** WSDL이 조금 변경되더라도 자바 코드가 유연하게 대처할 수 있도록 설계되었습니다.

### 장점
*   **Spring Boot와의 통합:** 의존성 추가(`spring-boot-starter-web-services`)만으로 설정이 거의 완료됩니다.
*   **유연성:** JAXB 등 마샬링(Marshalling) 라이브러리 교체가 쉽고 설정이 간편합니다.
*   **코드 가독성:** 어댑터 패턴이나 헥사고날 아키텍처 적용 시, Spring Bean으로 자연스럽게 통합됩니다.

### 단점
*   Contract-Last(자바 코드로 WSDL 생성) 방식은 거의 지원하지 않습니다.
*   JAX-WS 표준 어노테이션을 사용할 수 없습니다.

---

## 5. 결론: 무엇을 선택해야 할까?

### **Spring Web Services**를 선택하세요:
*   현재 프로젝트가 **Spring Boot** 기반이다.
*   이미 **WSDL 파일이나 XSD 스키마**가 존재한다 (Contract-First).
*   Spring의 DI, AOP, Exception Handling 등을 자연스럽게 사용하고 싶다.
*   JAX-WS 표준에 얽매일 필요가 없다.

### **Apache CXF**를 선택하세요:
*   **자바 코드를 먼저 작성**하고 나중에 WSDL을 생성해야 한다 (Contract-Last).
*   기존의 JAX-WS 기반 레거시 코드를 최소한의 수정으로 가져와야 한다.
*   하나의 서비스 인터페이스로 SOAP과 REST를 동시에 노출해야 한다.
*   매우 특수하고 복잡한 엔터프라이즈 보안 스펙(WS-Security의 깊은 기능 등)을 제어해야 한다.
