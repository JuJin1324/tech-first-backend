# TODO List

- [ ] **문서 업데이트**: `docs/LOGICAL_FLOW_DIAGRAM.md` 파일의 Mermaid 다이어그램을 현재 리팩토링된 구조(Pragmatic Layered Architecture)에 맞게 수정하기.
    - **변경 사항 반영**:
        - `CustomerUseCase` (Input Port) -> `CustomerService` (Interface)
        - `CustomerService` (UseCase Implementation) -> `CustomerServiceImpl` (Service Implementation)
        - `Adapter` 용어 -> `API Layer` (REST/SOAP Endpoint)
        - 패키지 구조 변경 반영 (`api`, `service`, `domain`)
