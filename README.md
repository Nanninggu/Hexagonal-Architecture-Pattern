### 이 프로젝트에 대한 설명
이 프로젝트는 Java와 Spring Boot를 사용하여 개발된 웹 애플리케이션입니다.  
이 프로젝트는 Hexagonal Architecture (또는 Ports and Adapters Architecture) 패턴을 따르고 있습니다.  
이 패턴은 애플리케이션의 코어 로직을 외부 세계로부터 격리시키는 것을 목표로 합니다.

프로젝트의 주요 구성 요소는 다음과 같습니다:

1. `User` : 사용자 정보를 나타내는 DTO(Data Transfer Object)입니다.
2. `UserService` : `UserPort` 인터페이스를 구현하며, `UserMapper`를 사용하여 데이터베이스 작업을 수행합니다.
3. `UserMapper` : MyBatis 어노테이션을 사용하여 SQL 쿼리를 정의하고, `UserPort` 인터페이스를 구현합니다.
4. `UserController` : HTTP 요청을 처리하고 `UserService`를 사용하여 사용자 관련 작업을 수행합니다.

또한, 이 프로젝트는 Gradle을 빌드 도구로 사용하고 있으며, PostgreSQL 데이터베이스와 연동되어 있습니다.  
MyBatis는 SQL 쿼리 매핑 및 실행을 담당하고 있습니다.

### DB 설정 관련 내용

`@Options(useGeneratedKeys = true, keyProperty = "id")` 설정은 MyBatis가 SQL 쿼리를 실행한 후에 생성된 키를 검색하도록 지시합니다.

### 이 프로젝트에서 코어 로직을 외부 세계로 부터 격리한 부분은 아래와 같다.
이 프로젝트에서 코어 로직은 `UserService` 클래스에 구현되어 있습니다. 이 클래스는 `UserPort` 인터페이스를 구현하며,  
이 인터페이스는 애플리케이션의 코어 로직을 정의합니다. `UserService`는 `UserMapper`를 사용하여 데이터베이스 작업을 수행합니다.

이렇게 인터페이스를 사용하면, 코어 로직은 외부 세계 (예: 데이터베이스, 웹 서버 등)로부터 격리됩니다.  
즉, `UserPort` 인터페이스는 코어 로직과 외부 세계 사이의 경계를 정의합니다.  
이 인터페이스를 구현하는 클래스 (`UserService`)는 코어 로직을 구현하며, 이 클래스는 외부 세계와의 상호작용을 위해 다른 클래스 (`UserMapper`)를 사용합니다.

이러한 방식은 Hexagonal Architecture (또는 Ports and Adapters Architecture) 패턴의 핵심 원칙을 따르고 있습니다.  
이 패턴은 애플리케이션의 코어 로직을 외부 세계로부터 격리시키는 것을 목표로 합니다.  
이를 통해 코어 로직은 외부 세계의 변화에 영향을 받지 않고, 테스트와 유지보수가 용이해집니다.

### UserService.java에서 implements UserPort에 대한 설명
`implements UserPort`를 사용하는 이유는 `UserService` 클래스가 `UserPort` 인터페이스에 정의된 메서드를 구현하도록 강제하기 위함입니다.

이렇게 인터페이스를 사용하면, `UserService` 클래스는 `UserPort` 인터페이스를 통해 정의된 "계약"을 따르게 됩니다.  
이 "계약"은 `UserService`가 어떤 메서드를 제공해야 하는지를 명시합니다.  
이를 통해 `UserService` 클래스의 사용자는 `UserPort` 인터페이스를 통해 `UserService`의 기능을 이해하고 사용할 수 있습니다.

또한, 이 방식은 Hexagonal Architecture (또는 Ports and Adapters Architecture) 패턴의 핵심 원칙을 따르고 있습니다.  
이 패턴은 애플리케이션의 코어 로직을 외부 세계로부터 격리시키는 것을 목표로 합니다.  
`UserPort` 인터페이스는 코어 로직과 외부 세계 사이의 경계를 정의하며, 이 인터페이스를 구현하는 `UserService` 클래스는 코어 로직을 구현합니다.  
이를 통해 코어 로직은 외부 세계의 변화에 영향을 받지 않고, 테스트와 유지보수가 용이해집니다.