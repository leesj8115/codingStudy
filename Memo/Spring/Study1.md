# 스프링 스터디 - 1회

## 개요
[김영한의 스프링 입문](https://www.inflearn.com/course/스프링-입문-스프링부트)을 보고 정리하는 스터디

## 기간
2021-08.25-29 (5일간)

## 환경설정 및 기초

### 실행했을 때 나오는 폴더 구조

![폴더구조 사진](https://user-images.githubusercontent.com/14111233/130898010-bd002b93-978a-4f42-b16a-502307c75d80.png) 

- gradle : 라이브러리와 버전을 관리하기 위한 도구
- src/main, src/test : 테스트 코드의 중요성이 크기 때문에, main과 별개의 폴더로 구조화 하고 있음.
- src/main/java, src/main/resources : 코드 파일과 그 외 필요한 파일을 나누어 관리할 수 있음 
- **build.gradle** : 해당 파일에 적혀있는 내용을 토대로 라이브러리를 관리한다.
  > `dependencies` 안에 라이브러리를 나열한다.  
  > `repositories` 에서 어디서 라이브러리를 가져올 것인지 설정한다.
- .gitignore : 깃 연동시에 업로드 하지 않을 파일들을 마스킹

### 라이브러리
- 스프링 부트에서 필요한 라이브러리를 추가하면, 그와 관련된 라이브러리까지 자동으로 연결되서 편리하다!
- `spring-boot-starter-web`은 `tomcat`과 `spring-webmvc`를 포함하고 있다.
- 로그 관리를 위해 `slf4j`와 `logback`을 사용한다. (기본으로 활용)
- 테스트를 위해서는 `Junit`을 사용한다.
- 처음 학습할 때는 매번 tomcat을 재부팅하는데, `spring-devtools` 라이브러리를 사용하면 매번 재실행할 필요가 없다!

### 빌드
윈도우에서는 해당 프로젝트 폴더에서 `gradlew build`를 실행한다. 이후 `(프로젝트폴더)/build/libs`안에 있는 `(프로젝트).jar`를 java를 통해 실행하여 서버를 운용할 수 있다. 내장되어있는 톰캣 덕분에, 이 파일을 통해서 바로 운용할 수 있다.

### 웹을 개발하는 방법
1. 정적 컨텐츠 : 단순 HTML을 출력 (파일을 그대로 브라우저에 전달)
2. MVC와 템플릿 엔진 : HTML을 템플릿 엔진을 통해서 동적으로 표현 (서버에서 적절한 가공을 한 후에 전달)
3. API : 호출시 데이터를 전달 한다. (서버끼리 데이터를 교환할 때, 클라이언트에 데이터를 전달할 때). 주로 `json` 방식으로 사용한다.

### MVC 모델
**Model**, **View**, **Controller**로 역할을 나누어 각각의 책임을 나눈다. 컨트롤러는 템플릿 엔진에게 요청을 하고, 그 결과로 나온 HTML을 뷰로 전달한다.  
- eclipse에서 프로젝트 생성시, `java/spring` 하위에 여러 폴더를 만들어서 

### API 모델
`ResponseBody`를 통해 값 그대로를 전달하여 사용. 클래스 형태로 담아서 전달하면 요청 형태에 따라 변환하여 전송한다. 기본 값으로는 `json` 형태로 전송한다. 이런 방식이 가능한 것은, 요청에 따라 컨트롤러에서 컨버터로 전달하고, 이후에 브라우저로 전달되기 때문이다.

## 예제

### 기본적인 앱 구조
예제를 따라가며 학습하되, 입문에서는 가장 단순한 형태로 설계. **컨트롤러**, **서비스**, **도메인**, **리포지토리**로 나누어 설계한다.
- 리포지토리 : 데이터베이스에 접근하며, 도메인 객체를 DB에 저장하고 관리한다. DB와의 연계는 리포지토리를 통해서만 이뤄진다. (예제에서는 데이터베이스를 선정하지 않은 상태로 진행)
- 도메인 : 데이터베이스에 주로 저장되고 관리되는 객체. 예시) 회원, 주문, 쿠폰 등
- 서비스 : 핵심 비즈니스 로직. 처리 연계는 인터페이스를 이용하여 처리하고, 차후에 데이터베이스 결정에 따라 구체적인 객체를 만들어 처리

### Member 선언과 리포지토리 작성
member는 id와 name으로만 이뤄져있으며, 리포지토리에 대한 작업도 `Member save(Member member)`, `Optional<Member> findById(Long id)`, `Optional<Member> findByName(String name)`, `List<Member> findAll()`만 이뤄진다. 데이터베이스가 결정되지 않았기 때문에, 메모리 상에서만 작업하도록 `MemoryMemberRepository`를 구현하여 사용한다.  

이 때 `Optional`을 사용했는데, null이 반환될 경우가 있다면 Optional로 한 번 더 감싸주어 처리한다. 이렇게 처리하면 널로 인한 예외가 처리 가능하다.
- `of()` 혹은 `ofNullable()`로 객체를 생성. (null을 포함하여 객체를 생성하느냐, 아니냐의 여부 차이)
- 생성된 객체에서 `get()`을 통해 값에 조회하는데, 이 때 null인지 확인하기 위해 `isPresent()`로 확인하여 널인 상항에 대해 처리할 수 있다.

`findByName`을 처리할 때, 람다와 고차함수를 사용했다. 자바스크립트에서 보던 고차함수와 비슷하다. ~~고마워요 부스트캠프~~ `findAny()`를 통해 검색된 결과 하나를 반환했다. findAny 외에도 `stream`과 함께 여러 메소드를 사용한다. [출처](http://tcpschool.com/java/java_stream_terminal)
- 출력 : `forEach()`
- 소모 : `reduce()`
- 검색 : `findFirst()`, `findAny()` (병렬 요소일 때는 findAny 사용)
- 통계 : `count()`, `min()`, `max()`
- 연산 : `sum()`, `average()`
- 수집 : `collect()` (`toList()`, `toSet()` 등 `Collectors`의 메소드를 통해 다시 자료형으로 묶어준다.)


### 테스트 케이스
기능을 테스트하기 위해 테스트 코드를 별도로 준비하기보다, `JUnit` 프레임워크를 통해 테스트를 별도(?)로 돌려, 처리할 수 있다.
`org.junit.jupiter.api.Assertions` 또는 `org.assertj.core.api.Assertions`를 import 한 후, 해당 Assertions 의 메소드를 통해 두 객체, 혹은 값을 비교하며 처리한다. 실제 코드에 포함되지 않기 때문에, 한글로 함수명을 정하기도 한다.

![임의로 틀림](https://user-images.githubusercontent.com/14111233/130960236-eb6d2f1a-acad-47fa-9e64-2d6ec5482daa.png)  

틀렸을 경우에는 다음처럼 비교값과 결과값을 보여주며 틀렸음을 명확하게 알려준다.

여러 테스트들을 만들어 한 번으로 모두 실행하여 확인할 수 있다. 단, 메소드 실행 순서는 보장되지 않는다. 각 독립적으로 테스트하도록 하기 위해선, 테스트한 데이터를 지워줄 필요가 있다. 이 때 `@AfterEach`를 통해 테스트 종료시마다 실행되는 함수에 리포지토리를 초기화하는 코드를 넣어 처리할 수도 있다.  
혹은 테스트를 위해 초기값을 선언할 때 `@BeforeEach`를 통해 초기 값을 선언할 수도 있다.

테스트를 처음 작성할 때는 `given-when-then` 패턴으로 작성하는 것에 익숙해지면 좋다.
- given : 주어진 조건
- when : 실행할 내용
- then : 예상되는 결과

테스트 확인을 위해 try-catch를 해주는 것도 좋으나, `assertThrows` 메소드를 통해, 어떤 익셉션이 발생해야하는지도 설정하여 테스트를 할 수 있다.


여러 사람이 작업을 하는 코드에서는 **테스트가 필수**이다. 필요한 기능을 테스트할 수 있도록 연습하자.

### Optional
앞서 얘기했듯, null이 반환될 수 있는 경우에 대비하여 `Optional`로 한 번 감싼 뒤 반환하고 있다. Optional이 갖고있는 다양한 메소드를 통해 검증 및 처리할 수 있다. (단순 if-else하는 것보다 더 안전하다.) (`orElseGet()`, `isPresent()` 등..)

## 스프링과 빈의 의존관계
스프링에서는 의존관계를 설정하기 위해 `Bean`을 등록한다. 보통 `@Controller`, `@Repository`, `@Service` 등과 같은 어노테이션을 붙여서 컨테이너에 등록한다. 스프링은 실행할 때 해당 컨테이너의 객체를 관리하여, 객체의 생성 및 활용을 응용 프로그램에게 맡긴다. 단일 객체안에서 관리되도록 하기 위해, `@Autowired` 어노테이션을 통해 하나의 객체만 가져오도록 한다.(싱글톤!) 이러한 방식은 **컴포넌트 스캔** 방식이다. 각 어노테이션은 `@Component`를 갖고 있다.

물론 설정 파일에 등록하여 직접 빈을 등록할 수도 있다. 설정 파일을 만들어 `@Configuration` 클래스 안에 `@Bean`으로 해당 클래스를 반환하는 함수를 만들어, 호출할 때마다 등록된 함수를 호출하여 관리할 수 있도록 한다. 다만, **컨트롤러**는 어쩔 수 없다..  

그럼에도 직접 등록하는 방법이 필요한 이유는, 기존 코드를 변경하지 않고 각 모듈의 교체가 편리하기 때문이다. (Repository의 구현체를 변경할 때, 일일히 어노테이션을 붙이고 지우는 것 보다 Bean을 직접 등록해서 객체 생성을 관리해주는 것이 더욱 편리하기 때문이다.)

### 의존성 주입 방법
DI(의존성 주입)에는 **필드 주입**, **setter 주입**, **생성자 주입** 방법이 있다.
- 필드 주입
```java
@Autowired
private MemberService memberService;
```
- setter 주입
```java
private MemberService memberService;

@Autowired
public void setMemberService(MemberService memberService) {
  this.memberService = memberService;
}
```
- 생성자 주입
```java
private final MemberService memberService;

@Autowired
public MemberController(MemberService memberService) {
  this.memberService = memberService;
}
```
이렇게 3개지 방법이 있으나, 각 단점 (setter는 또 호출될 수도 있고, 필드는 다른 것으로 바꿔 끼우기가 어렵다)으로 인해 **생성자 주입** 방법을 선호한다.

## 스프링 DB 접근 기술

### H2 데이터베이스
H2 데이터베이스를 설치했다. 윈도우 인스톨러를 실행했고, 설치 경로의 `h2w.bat`를 실행하여 실습을 따라했다. `test.mv.db` 파일이 `C:\Users\사용자명\test.mv.db` 경로에 생성된다. `jdbc:h2:tcp://localhost/~/test` 를 통해 접근해서 사용한다.

### DataSourceUtils
```java
  private Connection getConnection() {
    return DataSourceUtils.getConnection(dataSource);
  }

  private void close(Connection conn) throws SQLException {
    DataSourceUtils.releaseConnection(conn, dataSource);
  }
```
`DataSourceUtils`를 통해서 커넥션을 연결해야 한다. 하나의 연결을 유지해서 사용하기 위해, 다음과 같은 메소드 형태로 호출, 반환한다.

### 구현 모듈 변경
스프링은 컨테이너를 통해, 모듈 변경을 편리하게 할 수 있다.
```java
@Configuration
public class SpringConfig {
  
  private DataSource dataSource;

  @Autowired
  public SpringConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    // return new MemoryMemberRepository();
    return new JdbcMemberRepository(dataSource);
  }
}
```
`SpringConfig.java`에서의 설정을 통해, 기존의 코드를 변경하지 않고 연결된 모듈을 변경했다. (MemoryMemberRepository -> JdbcMemberRepository)  
이러한 방법으로, 스프링의 의존성 주입을 통해 SOLID 원칙 중 하나인 **개방-폐쇄 원칙**을 준수할 수 있다.

### 테스트 케이스
이전에는 `MemoryMemberRepository`에 대한 테스트였고, 실행할 때마다 새로 생성해주었기에 큰 문제가 없었다. 하지만 이번부터는 데이터베이스와 연결해서 테스트하기 때문에 생각치못한 문제가 발생한다. 이를 위해 `@SpringBootTest`와 `@Transactional`을 사용한다.
- `@SpringBootTest` : 스프링의 컨테이너를 이용하겠다는 의미. 이를 통해 테스트에서 `@Autowired`를 통해 가져온 객체로 테스트를 진행할 수 있다.
- `@Transactional` : 각 테스트 완료 후 롤백하여, 다른 테스트에 영향이 가지 않도록 한다. (데이터베이스에 반영하고 싶다면 `@Commit`을 사용할 수도 있다.)  

물론, 지금 확인한 통합 테스트 형식이 꼭 좋은 것은 아니다. (스프링 컨테이너 준비시간 등으로 실행 시간이 오래 걸릴수도 있다 등..) 앞서 테스트했던 형식은 단위 테스트인데, 이러한 단위 테스트 형식으로 세세하게 나누어서 확인할 수 있도록 구현하는 게 좋은 테스트 방식이다.

### JdbcTemplate
순수 JDBC에서 실행한 코드는, 매 실행 마다 반복되는 코드가 많다. `JdbcTemplate` 라이브러리를 이용하면, 반복되는 코드를 간략하게 줄일 수 있다. 이것을 사용하는 것 보다는, `Mybatis`나 `JPA`가 더 낫지 않을까 싶다.

### JPA
JdbcTemplate로 반복 코드를 줄였으나, 쿼리문은 직접 작성해야했다. 하지만 `JPA`를 이용하면 직접 쿼리를 짜지 않고도 객체 중심의 프로그래밍 작업을 할 수 있다. JPA는 인터페이스로서의 역할을 하고, 실제 구현 기술로 `hibernate`를 사용한다.
사용을 위해서는 `EntityManager`를 가져오는데, 이 때 스프링이 `application.properties`에 설정한 여러 내용을 취합해서 만든 객체를 가져온다. primary key를 통해 조회하는 경우에는 `em.find`를, 그 외에는 `em.createQuery`를 사용한다.

여기서, **Spring Data JPA** 프레임워크를 더하면 더욱 더 편리한 개발 환경을 얻을 수 있다. 선택이 아니라 필수일 정도로! 물론, JPA가 우선이고 스프링 데이터 JPA가 우선이 아니다. 처음 막무가내로 배웠을 때 두개의 구분을 하지 않고 배웠는데, 이번을 통해 둘이 명확하게 다른 개념임을 알았다.

`JpaRepository<>` 인터페이스를 확장하여 필요한 내용을 작성했다. 이 때, 정말 별다른 코드가 없다. 또한 별도의 클래스를 구현할 필요없이, JpaRepository에서 만들어주는 객체를 가져다 사용하면 된다. 내부에는 CRUD와 관련한 기본적인 메소드들이 정의되어 있다. 공통화가 불가능한 부분에 대해서는 규칙에 맞게 함수명을 정의하면, 원하는 함수를 만들수 있다.
```java
// select * from member where name = ?
@Override
Optional<Member> findByName(Stirng name);
```
실무에서는 복잡한 동적 쿼리는 `Querydsl`이라는 라이브러리를 사용한다. 여러 개의 라이브러리를 복합하여 사용하거나, 제한될 경우 JPA의 네이티브 쿼리를 이용해 처리한다.

## AOP
