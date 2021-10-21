# 면접 질문 스터디 - 6회차 (2021.10.21)

## 직렬화(Serialization)과 역직렬화(Deserialization)에 대해서 설명해 주세요.
> 자바에서 사용되는 데이터(혹은 객체)를 외부의 자바 시스템에서도 활용할 수 있도록 Byte 배열 형태로 데이터를 변환하는 기술을 말합니다. `Serializable` 인터페이스를 상속하여 구현한 객체는 직렬화 할 수 있게 됩니다.  
> 파일 저장, DB 저장 등의 작업을 위해서 데이터를 직렬화하여 전달합니다. 짧은 생명주기를 갖는 데이터에 적합하며, 자주 변경되거나 긴 생명주기를 갖는 데이터는 다른 포맷을 활용합니다. (JSON 등)


### 직렬화, 역직렬화 예시 [참고](https://sas-study.tistory.com/345)
직렬화
```java
class Scratch {
  public static void main(String[] args) {
    String byteString = serialize();
    System.out.println(byteString);
  }

  private static String serialize() {
    Member member = new Member("goodDabang", "wsh0821@station3.co.kr", 27);
    byte[] serializedMember;
    String serializedMemberStr = "";
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
        oos.writeObject(member);
        serializedMember = baos.toByteArray();  // Byte[]로 변환
        serializedMemberStr = Base64.getEncoder().encodeToString(serializedMember); // encoding 과정 실행
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return serializedMemberStr;
  }
}
```
클래스 객체를 Byte[]로, 이를 다시 encoding하여 해시 문자열로 변환했다.

역직렬화
```java
class Scratch {
  public static void main(String[] args) {
	  String serializedMemberStr = "";// 이전에 얻은 serializedMemberStr
    byte[] serializedMember = Base64.getDecoder().decode(serializedMemberStr);  // decoding 과정 실행
    deserialize(serializedMember);
  }

  private static void deserialize(byte[] serializedMember) {
    try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
      try (ObjectInputStream ois = new ObjectInputStream(bais)) {
        Object o = ois.readObject();
        Member o1 = (Member) o;
        System.out.println(o1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
```
앞선 결과물의 해시 문자열을 다시 원본 객체(Object)로 변환했다.

### 다른 Format의 직렬화 [참고2](https://nesoy.github.io/articles/2018-04/Java-Serialize)
![사진](https://nesoy.github.io/assets/posts/20180418/1.png)  

표 형태의 다량의 데이터를 직렬화 할 때는 CSV 형태를 활용, 구조적인 데이터는 XML이나 JSON을 활용한다.
- CSV : , 를 기준으로 데이터를 구분하는 방법 => `[Lee, Kim, Park]`
- XML : 태그<> 를 기준으로 데이터의 계층과 값을 표현하는 형식 => `<NAME>Lee</NAME>`
- JSON : `:`을 활용해 Key, Value 형태로 나타내는 형식 => `{name : Lee}`

### 자바 직렬화/역직렬화시 문제 [참고2]
 - InvalidClassException : 직렬화 한 객체를 다시 역직렬화했을 때, `java.io.InvalidClassException`이 발생할 수 있다. 이는 직렬화 시스템과 역직렬화 시스템이 다를 경우에 발생한다. 이를 해결하기 위해서 **SUID(serialVersionUID)** 를 활용한다. (값을 지정해주는 것이 좋다.)
 - 타입 변환시 : int -> long과 같은 변경에도 역직렬화시 익셉션이 발생한다. 원본 클래스의 타입을 엄격하게 지켜야 한다.
 - 멤버 변수가 비었을 경우 익셉션 발생이 아닌 null 값이 들어간다.
 - 데이터의 크기 : 직렬화에 따라 데이터의 크기가 달라질 수 있다. (더 커질수도, 작아질 수도)

## 스레드에서 Race condition에 대해서 설명해주세요.
> Race condition(경쟁 상태)란 여러 스레드가 공통된 자원에 접근했을 때, 서로 자원을 접근하기 위해 경쟁하는 상태를 말합니다. 자바에서는 Atomic, volatile, synchronized 등을 이용해 처리합니다.

### Critical Section (임계 영역) [참고1](https://zangzangs.tistory.com/115?category=458016)
접근을 제한하고자 하는 자원, 영역

### Race condition 해결 조건
- Mutual Exclusion (상호 배제) : 이미 임계 영역에 접근 중인 프로세스가 있을 경우 접근을 제한
- Progress(진행) : 임계 영역을 사용 중인 프로세스가 없다면 접근을 허용해야함.
- Bounded Waiting(한정 대기) : 상호 배제에 대한 대기가 무한하지 않고, 유한하도록 한다. 이는 기아 상태를 방지하기 위함이다.

### Atomic, Volatile, Synchronized [참고2](https://skasha.tistory.com/38)
- atomic : CAS 기반으로 처리. 처음과 끝의 변수 값을 저장하여, 일치하지 않을 경우 반영하지 않는다.
- volatile : 변수의 데이터를 캐시가 아닌 메모리에 저장하도록 하여, 가시성 문제를 해결한다. (하나의 스레드만 write를 하고 나머지의 스레드들은 read하는 상황에서만 최신의 값을 보장해준다.)
- synchronized

## StringBuilder를 사용하는 것과 String을 사용하여 연산하는 것에서 어떠한 큰 차이가 존재할까요? [참고](https://ifuwanna.tistory.com/221)
> String은 불변 객체로서, 값을 변경할 때 마다 새로운 객체를 생성합니다. 이는 계속하여 쓰레기를 만들어낼 수 있습니다.  
> StringBuilder 클래스는 가변성을 갖고 있어 문자열을 수정하는 것이 가능합니다. 따라서 수정이 잦을 경우는 스트링 빌더를 활용하는 게 좋습니다.

### StringBuilder와 StringBuffer의 차이
StringBuilder는 동기화를 지원하지 않는다. 단일 스레드 성능은 좋으나, 멀티 스레드 환경에서는 문제가 발생할 수 있다.  
StringBuffer는 동기화를 지원하기 때문에 멀티 스레드 환경에서 안전하다.  

![사진](https://t1.daumcdn.net/cfile/tistory/99BE23375E2F133722)

### String pool [참고](https://doohong.github.io/2018/03/04/java-string-pool/)
String 객체를 생성하는 방법으로 `new` 키워드를 활용한 방법과 리터럴(`""`)이 있다. new 키워드를 활용할 경우 각자 Heap 영역에 생성이 된다. 리터럴로 생성한 문자열은 Spring pool에 생성되어, 불변 문자열을 공유한다. 이는 String의 `intern()`메소드로 유무를 확인할 수 있다.

## Dispatcher-Servlet이란 무엇인가요?
> 스프링에서 HTTP 프로토콜을 통해 들어오는 모든 요청을 가장 먼저 확인하고 처리하는 컨트롤러입니다. 프론트 컨트롤러로 불리기도 합니다. 컨트롤러 맵핑을 처리해주기 때문에, web.xml에 일일히 등록할 필요가 없습니다.

### Dispatcher-Servlet의 존재 의의 [참고](https://velog.io/@seculoper235/2.-DispatcherServlet-이란)
기존의 웹 환경에서 web.xml에서 모든 서블릿을 URL과 매핑하여 처리해야했지만, 스프링 MVC에서는 디스패쳐 서블릿이 **모든 요청을 핸들링**하고 **공통 작업을 처리**하면서 편리하게 기존의 불편함을 해결했다.  
![사진3](https://blog.kakaocdn.net/dn/MRXX3/btqK7014LOZ/wMKgG9RVkdZR7Ag5nD4wh0/img.png)

### 정적 파일 요청은? [참고](https://mangkyu.tistory.com/18)
처리를 프론트 컨트롤러에서 해결해주는 건 좋았으나, 모든 요청을 처리하다보니 정적 파일에 대한 요청도 가로채고 반환하지 못하는 문제가 생겼다.

이를 해결하기 위해서
1. 요청에 대한 컨트롤러를 찾고, 찾을 시 해당 컨트롤러를 통해 처리
2. 찾을 수 없을 경우 설정된 자원(Resource) 경로를 탐색하여 자원을 반환

이를 통해 효율적인 리소스 관리와 확장에 용이한 장점을 갖게 되었다.


## Spring AOP는 CTW, LTW, RTW 중에 무엇이고 Aspectj 와 비교하여 언제 사용하는 것이 좋고 언제 사용하지 않는 것이 좋을까요? [참고](https://logical-code.tistory.com/118)
> 스프링 AOP는 RTW 방식을 활용하고 있습니다. 스프링 컨테이너에서 관리하는 Bean에 대해 적용하기 위해 활용합니다. AspectJ는 완전한 AOP 기능을 제공하기 위해 나왔으며, 다양한 포인트컷을 제공하고 모든 객체에 적용이 가능합니다. 담나 CTW 방식을 활용하여 추가적인 설정이 필요합니다.

### 용어 설명 [참고](https://shlee0882.tistory.com/206)
AOP는 **관심사의 분리(기능의 분리), 핵심적인 기능에서 부가적인 기능을 분리**한다. 핵심 기능과 부가 기능을 나누고, 부가 기능을 에스펙트(Aspect)라는 모듈로 만들고 개발하는 방법이다.

- Target : 핵심 기능을 담고있는 모듈. 부가 기능을 부여할 대상
- Advice : **부가 기능**을 담고 있는 모듈
- JoinPoint : Advice를 적용 가능한 지점. 메소드 호출, 필드 값 변경 등의 **특정 작업이 시작되는 지점**을 일컫는다.
- PointCut : 조인포인트의 부분 집합. 실제 Advice가 적용되는 조인포인트. 정규 표현식 혹은 AspectJ 문법을 활용한다.
- Aspect : AOP의 기본 모듈. Advice와 PointCut을 합친 것이다. 싱글톤 형태의 객체로 존재한다. (스프링 AOP에서는 Advisor(어드바이저)로 부른다.) 
- 위빙(Weaving) : 포인트컷에 의해 결정된 타겟의 조인포인트에 부가기능(어드바이스)를 삽입하는 과정. 타겟의 코드에 영향을 주지 않고 부가기능을 추가할 수 있도록 하는 핵심적인 처리 과정이다.ㅋ
- proxy : 타겟에 Aspect를 적용하여 생성하는

### AspectJ [참고](https://jaehun2841.github.io/2018/07/22/2018-07-22-spring-aop4/#aspectj란)
자가 프로그래밍 언어용 관점 지향 프로그래밍(AOP) 확장 기능. 스프링에서는 `@Aspect` 어노테이션을 바탕으로 로직을 작성할 수 있어 xml 방식에 비해 편리하다. 스프링 뿐만 아니라 AOP를 지원하지 않는 프레임워크에서도 AOP를 지원하도록 도움을 준다.

### RTW, CTW, LTW [참고](https://dahye-jeong.gitbook.io/spring/spring/2020-04-10-aop-aspectj) [참고2](https://mongdal.tistory.com/18)

![사진](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile3.uf.tistory.com%2Fimage%2F992B3A3C5AB947A221DB83)  

1. RTW(RunTime Weaving) : Spring AOP에서 사용하는 방식. 실제 런타임시 Method 호출 시에 위빙을 이룬다. 소스 파일, 클래스 파일의 변형이 없다는 장점이 있으나 PointCut에 대한 Advice 수가 늘어날 수록 성능이 떨어진다.
2. CTW(Compile Time Weaving) : AspectJ에서 사용하는 방식. 컴파일 과정에서 바이트 코드 조작을 통해 Advisor 코드를 직접 삽입하여 위빙한다. 가장 빠른 속도를 가지나, lombok과 같이 컴파일 과정에서 코드를 조작하는 다른 플러그인과 충돌할 가능성이 있다.  
![사진](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FVC46l%2FbtqJxGD3pTa%2F01PHkGvRGOmqwqlLpt9KQ1%2Fimg.png)
3. LTW(Load Time Weaving) : ClassLoader를 이용해 클래스가 JVM에 로드될 때 바이트 코드 조작을 통해 위빙하는 방식. 컴파일 시간은 상대적으로 CTW보다 빠르나 메모리에 오브젝트가 올라가는 과정에서 위빙이 발생하기 때문에, 런타임은 상대적으로 느리다.
