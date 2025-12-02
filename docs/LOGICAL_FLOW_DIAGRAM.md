# [Hexagonal Architecture] Logical Request Flow Diagram

이 다이어그램은 `tech-first-backend` 프로젝트의 헥사고날 아키텍처(Pragmatic Layered Architecture 적용)에서 외부 클라이언트의 요청이 어떻게 애플리케이션 코어의 `CustomerService`로 전달되고 처리되는지 논리적인 흐름을 시각화합니다.

물리적인 구현체보다는 **역할과 흐름**에 초점을 맞추어, 다양한 클라이언트가 어떻게 동일한 핵심 비즈니스 로직에 접근하는지 보여줍니다.

```mermaid
graph TB
    %% External Clients
    A[SOAP 클라이언트] 
    C[REST 클라이언트]
    
    %% API Layer (Driving Adapters)
    subgraph API_Layer ["API Layer (Inbound Adapters)"]
        B[CustomerSoapEndpoint<br/>SOAP Endpoint]
        D[CustomerRestController<br/>REST Controller]
    end
    
    %% Service Layer (Application Core)
    subgraph Service_Layer ["Service Layer (Application Core)"]
        E{{CustomerService<br/>Input Port (Interface)}}
        F[MockCustomerService<br/>Service Implementation]
    end
    
    %% Domain Layer
    subgraph Domain_Layer ["Domain Layer"]
        G[Customer<br/>Domain Entity]
    end
    
    %% Output Port (Example)
    subgraph Infrastructure_Layer ["Infrastructure Layer (Outbound)"]
        H{{CustomerRepository<br/>Output Port (Interface)<br/>(Not Implemented)}}
    end

    %% Request Flow (Inbound)
    A --> B
    C --> D
    B --> E
    D --> E
    E -.implements.-> F
    F --> G
    
    %% Data Flow (Outbound)
    G -.used by.-> H

    %% Styling
    classDef client fill:#FFE6E6,stroke:#CC0000,stroke-width:2px,color:#000
    classDef apiLayer fill:#E6F3FF,stroke:#0066CC,stroke-width:2px,color:#000
    classDef interface fill:#E6FFE6,stroke:#00CC00,stroke-width:3px,color:#000
    classDef implementation fill:#F0FFE6,stroke:#66CC00,stroke-width:2px,color:#000
    classDef domain fill:#FFF9E6,stroke:#CCAA00,stroke-width:2px,color:#000
    classDef infra fill:#FFE6F9,stroke:#CC00AA,stroke-width:3px,color:#000

    class A,C client
    class B,D apiLayer
    class E interface
    class F implementation
    class G domain
    class H infra
```