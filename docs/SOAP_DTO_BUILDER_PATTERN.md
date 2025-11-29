# [SOAP] JAXB DTO에서 Setter 대신 Builder 패턴 사용하기

SOAP 웹 서비스를 개발하다 보면, 요청(Request)과 응답(Response)을 처리하는 DTO 클래스들이 **Setter 패턴**으로 도배되는 것을 흔히 볼 수 있습니다.

최근의 자바 개발 트렌드는 객체의 불변성(Immutability)과 안전성을 위해 **생성자 주입**이나 **Builder 패턴**을 지향하는데, 왜 유독 SOAP/JAXB 관련 코드에서는 여전히 Setter가 많이 보일까요? 그리고 이를 개선할 방법은 없을까요?

## 1. 왜 SOAP DTO는 Setter를 많이 쓸까?

범인은 바로 **JAXB (Java Architecture for XML Binding)**의 동작 방식 때문입니다.

1.  **기본 생성자 필수:** JAXB는 XML 데이터를 객체로 변환(Unmarshalling)할 때, 리플렉션(Reflection)을 사용해 **매개변수가 없는 기본 생성자(No-args Constructor)**를 호출하여 객체를 먼저 생성합니다.
2.  **JavaBeans 규약:** 객체 생성 후, XML의 값을 필드에 채워 넣을 때 전통적으로 `setXXX()` 메소드를 호출하는 방식을 사용했습니다.
3.  **자동 생성 도구의 기본값:** XSD 스키마 파일로부터 자바 코드를 자동 생성해주는 도구(`xjc` 등)들이 기본적으로 Setter가 포함된 가변 객체(Mutable Object)를 만들어냅니다.

이러한 이유로 `Endpoint` 클래스에서 응답 객체를 만들 때 다음과 같이 장황한 Setter 코드가 작성되곤 합니다.

```java
// 기존 방식: Setter 지옥
GetCustomerResponse response = new GetCustomerResponse();
response.setId(customer.getId());
response.setName(customer.getName());
response.setEmail(customer.getEmail());
return response;
```

---

## 2. Setter를 없애고 Builder 패턴 적용하기

JAXB를 사용한다고 해서 반드시 Setter를 열어둬야 하는 것은 아닙니다. **JAXB 설정과 Lombok을 적절히 조합**하면, 안전하고 깔끔한 Builder 패턴을 사용할 수 있습니다.

### 핵심 포인트
1.  **`@XmlAccessorType(XmlAccessType.FIELD)`:** JAXB가 Getter/Setter 메소드를 통하지 않고, **필드(멤버 변수)에 직접 접근**하여 값을 읽고 쓰도록 설정합니다.
2.  **`AccessLevel.PROTECTED` 생성자:** JAXB가 객체를 생성할 수 있도록 **기본 생성자**는 반드시 필요합니다. 하지만 굳이 `public`일 필요는 없습니다. 외부에서 함부로 빈 객체를 만들지 못하도록 `protected`로 막아둡니다.
3.  **`@Builder`:** 객체 생성을 위한 빌더 패턴을 적용합니다.

### 개선된 코드 예시

```java
import jakarta.xml.bind.annotation.*;
import lombok.*;

@XmlAccessorType(XmlAccessType.FIELD) // 1. 필드 직접 접근
@XmlRootElement(name = "getCustomerResponse")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 2. JAXB용 생성자 (외부 차단)
public class GetCustomerResponse {

    @XmlElement(required = true)
    private long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String email;

    // 3. 빌더 패턴 적용
    @Builder
    public GetCustomerResponse(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
```

---

## 3. 결과 비교

이제 `Endpoint`에서 응답 객체를 생성하는 코드가 훨씬 직관적이고 깔끔해집니다.

### Before (Setter 사용)
```java
GetCustomerResponse response = new GetCustomerResponse();
response.setId(1L);
response.setName("Tech First");
response.setEmail("tech@first.com");
```
*   객체가 불완전한 상태로 생성될 수 있음.
*   코드가 길어지고 가독성이 떨어짐.

### After (Builder 사용)
```java
GetCustomerResponse response = GetCustomerResponse.builder()
    .id(1L)
    .name("Tech First")
    .email("tech@first.com")
    .build();
```
*   객체 생성 시점에 필요한 데이터가 명확히 보임.
*   불필요한 Setter 노출을 막아 객체의 안전성 향상.

## 요약
SOAP 프로젝트에서도 **`@XmlAccessorType(XmlAccessType.FIELD)`**와 **`Protected 기본 생성자`** 조합을 활용하면, 레거시스러운 Setter 코드에서 벗어나 모던한 자바 코드를 작성할 수 있습니다.
