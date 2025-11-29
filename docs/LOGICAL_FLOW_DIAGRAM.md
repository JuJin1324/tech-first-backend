# [Hexagonal Architecture] Logical Request Flow Diagram

이 다이어그램은 `tech-first-backend` 프로젝트의 헥사고날 아키텍처에서 외부 클라이언트의 요청이 어떻게 애플리케이션 코어의 `CustomerUseCase`로 전달되고 처리되는지 논리적인 흐름을 시각화합니다.

물리적인 구현체보다는 **역할과 흐름**에 초점을 맞추어, 다양한 클라이언트가 어떻게 동일한 핵심 비즈니스 로직에 접근하는지 보여줍니다.

```mermaid
graph TB
    %% External Clients
    A[SOAP 클라이언트] 
    C[REST 클라이언트]
    
    %% Inbound Adapters (Primary/Driving Adapters)
    B[CustomerSoapEndpoint<br/>SOAP 어댑터]
    D[CustomerRestController<br/>REST 어댑터]
    
    %% Application Core - Port
    E{{CustomerUseCase<br/>입력 포트 인터페이스}}
    
    %% Application Core - Service Implementation
    F[CustomerService<br/>유스케이스 구현체]
    
    %% Domain Layer
    G[Customer 도메인<br/>비즈니스 로직 & 규칙]
    
    %% Output Port (구현 생략)
    H{{CustomerRepository<br/>출력 포트 인터페이스<br/>구현 생략}}

    %% Request Flow (Inbound)
    A --> B
    C --> D
    B --> E
    D --> E
    E -.구현.-> F
    F --> G
    
    %% Data Flow (Outbound)
    G --> H

    %% Styling
    classDef client fill:#FFE6E6,stroke:#CC0000,stroke-width:2px,color:#000
    classDef inboundAdapter fill:#E6F3FF,stroke:#0066CC,stroke-width:2px,color:#000
    classDef inputPort fill:#E6FFE6,stroke:#00CC00,stroke-width:3px,color:#000
    classDef service fill:#F0FFE6,stroke:#66CC00,stroke-width:2px,color:#000
    classDef domain fill:#FFF9E6,stroke:#CCAA00,stroke-width:2px,color:#000
    classDef outputPort fill:#FFE6F9,stroke:#CC00AA,stroke-width:3px,color:#000

    class A,C client
    class B,D inboundAdapter
    class E inputPort
    class F service
    class G domain
    class H outputPort
```
