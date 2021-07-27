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
  
- build
  - 빌더 패턴(Builder pattern) 이란 복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴이다
  - 많은 파라미터를 갖고 있는 생성자를 사용할 경우 생성자의 매개변수 수를 통제하기 어렵다.
    - 이해하기 어려워 진다.
  - 생성자 오염을 피하면서 객체의 다양한 특징을 만들 수 있습니다. 객체의 여러 특징이 있을 수 있을 때 유용합니다. 또는 객체 생성과 관련된 많은 단계가 있는 경우.
  - 빌더 객체를 통해 구체적인 객체를 생성한다.
  
```java
//빌더 예시
package builder;

public class user {
  private String name;
  private int age;


  private int like_num;

  public String getName() {
    return name;
  }

  public int getLike_num() {
    return like_num;
  }


  public int getAge() {
    return age;
  }


  private user(userBuilder builder){
    this.name = builder.name;
    this.age = builder.age;

  }

  //build class
  public static class userBuilder{
    private String name;
    private int age;
    private int like_num;

    public userBuilder setLike_num(int like_num){
      return this;
    }

    public userBuilder(String name,int age){
      this.name=name;
      this.age= age;
    }


    public user build(){
      return new user(this);
    }

  }

  public static void main(String args[]){

    user test = new user.userBuilder("test1",15)
            .build();

    System.out.println("test.name " + test.getName());
    System.out.println("test.getAge " + test.getAge());
    System.out.println("test.getLike_num " + test.getLike_num());
  }
}

```


  ## 어노테이션

- @RequiredArgsConstructor
  - 선언된 모든 final필드가 포함된 생성자를 생성해 준다.
  - final이 없는 필드는 생성자에 포함되지 않는다.
  
- @RequestParam("name") String name
  - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
  
- @SpringBootTest
  - H2데이터베이스를 자동으로 실행해 준다.

- @Builder
  - build

- ### Test어노테이션
  - @WebMvcTest 
    - JPA기능이 작동하지 않는다
  - @SpringBootTest
    - @WebMvcTest의 경우  JPA 기능이 작동하지 않기 떄문에  @SpringBootTest와 TestRestRemplate를 사용하기
  - @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    -  사용하지 않는 랜덤 포트 사용
  - @DataJpaTest : JPA 관련된 컴포넌트만 로드된다.    
  - TestRestTemplate 
    - httpsAPI와 통신이 잘되고, 통합테스트에 매우 유용하다
    - 


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

- Service
  - 트랜잭션,도메인 간 순서 보장의 역할
  
- Spring 웹 계층
  - WebLayer : 외부 요청과 응답에 대한 전반적인 영역 (컨트롤러 뷰 템플릿 영역)
  - Service Layer 
    - 컨트롤러와 Dao의 중간 영역, @Transactional이 사용되어야 하는 영역
  - Repository Layer : 
    - Database와 같이 데이터 저장소에 접근하는 영역
  - Dtos
    - 계층 간에 데이터 교환을 위해 객체를 이야기한다. 
    - ex) 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등
  - Domain Model
    - 도메인이라 불리는 개발 대상을 모든 사람이 동이랗ㄴ 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것을 도메인 모델이라고 한다
    - ex) 택시앱 배차, 탑승 , 요금 등이 모두 도메인이 될 수 있다.
    - @Entity가 사용된 영역 역시 도메인 모델이다
    - 무조건 데이터베이스의 테이블과 관계가 있어야만 하는것은 아니다
    - VO처럼 값 객체들도 이 영역에 해당한다.
    - 비즈니스 처리를 담당하는 곳
    - 도메인 모델을 처리하면 서비스 메소드는 트랜잭션과 도메인 간의 순서만 보장해준다.

- 트랜잭션 스크립트
  - 모든 로직이 서비스 클래스 내부에서 처리된다.
  
- Bean객체 주입받기 
  - @autowired대신 생성자 주입방식 쓰기! (@RequiredArgsContructor사용해서 빈객체 얻기)
  - 롬북을 사용하면 생서자 코드를 계속해서 수정하는 번거로움을 해결할 수 있다.
  
- 스프링 데이터 JPA가 제공하는 Repository의 모든 메소드는 @Transactional이 적용되어 있다.
  - test코드 사용할 떄 Transaional안넣어도됨