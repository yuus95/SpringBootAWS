# SpringBootAWS

# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스  따라만들기

---


- git
    - 대부분은 프로젝트 이름을 깃허브 저장소와 같은 이름을 사용한다.
    

- ### TDD
  - 테스트가 주도하는 개발(테스트 코드를 먼저 작성)
  - 단계
    - 항상 실패하는 테스트를 먼저 작성하고(RED)
    - 테스트가 통과하는 프로덕션 코드를 작성하고(Green)
    - 테스트가 통과하면 프로덕션 코드를 리팩토링한다(Refactor)
  

  - 단위 테스트
    - TDD의 첫 번째 단계인 기능 단위의 테스트 코드를 작성하는 것
    - 이점
      - 단위 테스트는 개발단계 초기에 문제를 발견하도록 도와준다.
      - 단위 테스트는 개발자가 나중에 코드를 리팩토링하거나 라이브러리 업그레이드 등에서 기존 기능을 올바르게 작동하는지 확인할 수 있다.
      - 단위 테스트는 기능에 대한 불확실성을 감소시킬 수 있다.
      - 단위 테스트는 시스템에 대한 실제 문서를 제공할 수 있다.
  

  ## 어노테이션

- @RequiredArgsConstructor
  - 선언된 모든 final필드가 포함된 생성자를 생성해 준다.
  - final이 없는 필드는 생성자에 포함되지 않는다.
  
- @RequestParam("name") String name
  - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
  
- @SpringBootTest
  - H2데이터베이스를 자동으로 실행해 준다.

  ## Spring Data JPA

- JPA (인터페이스)<- Hibernate(구현체) <- Spring Data JPa(추상화 모듈)

- 등장 이유
  - 구현체 교체의 용이성
    - Hibernate외에 다른 구현체로 쉽게 교체하기 위함
  - 저장소 교체의 용이성
    - 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체하기 위함
  

- Entity 클래스에서는 절대 Setter메소드를 만들지 않기.
  - 생성자를 통해 최종값을 채운후 DB에 삽입하기
  

- mockMvc 에 필요한 라이브러리
```java

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

```