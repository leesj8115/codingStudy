# 면접 질문 스터디 - 8회차 (2021.10.28)

## 불변 객체는 무엇이고 Java에서 어떻게 구현할까요? [참고](https://velog.io/@conatuseus/Java-Immutable-Object불변객체)
> 불변 객체는 한 번 값을 할당했을 때 값을 변경할 수 없는 객체입니다. 물론 재할당은 가능합니다. 이를 구현하기 위해 `final` 키워드와 조합하여 사용합니다. 참조형 자료의 경우 수정을 할 수 없도록 조작이 필요합니다.

### 불변객체 생성을 위한 규칙 [참고](https://mangkyu.tistory.com/131)
1. 클래스를 final로 선언하라
2. 모든 클래스 변수를 private와 final로 선언하라
3. 객체를 생성하기 위한 생성자 또는 정적 팩토리 메소드를 추가하라
4. 참조에 의해 변경가능성이 있는 경우 방어적 복사를 이용하여 전달하라

## hashCode란 무엇인가요?
> 객체를 구분짓기 위한 고유한 정수값을 말합니다. 객체의 내부 주소를 정수값으로 변환하여 반환합니다.

### `hashCode()`와 `equals()`의 관계 [참고](https://jisooo.tistory.com/entry/java-hashcode와-equals-메소드는-언제-사용하고-왜-사용할까)
`equals()`는 해시코드가 같은지 확인한 후 데이터의 일치 유무를 확인한다. 때문에 equals를 오버라이드 한다면 hashCode도 오버라이드 해주는 것이 좋다.


## CGI는 무엇이고 Servlet API는 무엇이며, 어떤 차이가 있나요? [참고](https://ko.gadget-info.com/difference-between-cgi)
> CGI는 Common Gateway Interface로, 클라이언트가 웹 서버로부터 동적 컨텐츠를 제공받기 위한 규격을 말합니다. 서블릿 API는 자바를 기반으로하는 동적 컨텐츠 제공 방법입니다. 서블릿은 플랫폼에 의존적이지 않으며, 요청마다 프로세스를 생성하는 CGI와 달리 스레드를 통해 요청을 처리하기 때문에 더욱 효율적입니다.

### CGI 대비 서블릿의 장점 [참고](https://jongminlee0.github.io/2020/10/10/cgivsservlet/)
1. Better performance : 요청에 따라 process가 아닌 thread 생성
2. Portability : 자바 사용
3. Robust : GC를 통한 자원(메모리) 관리
4. Secure : 스크립트에 비해 자바를 통하므로 안전

## Bean Lite Mode는 무엇인가요?
> `@Configuration` 어노테이션을 통해 생성되는 Bean은 cglib를 통해 프록시 객체로서 생성됩니다. 하지만 `@Component`와 함께 조합하여 생성한 Bean은 순수한 객체로서 반환합니다. 때문에 호출시마다 다른 객체를 반환합니다.

### 왜 활용할까? [1](https://developpaper.com/do-you-really-understand-spring-java-config-full-configuration-vs-lite-bean-mode/) [2](https://hyojabal.tistory.com/25)
이를 통해 얻는 이점이 무엇일까?

## 프레임워크와 라이브러리 차이는 무엇인가요? [1](https://engkimbs.tistory.com/673) [2](https://curryyou.tistory.com/363)
> 프레임워크는 개발을 위해 필요한 기반을 제공해주는 클래스와 인터페이스의 집합입니다. 라이브러리는 특정 기능에 대한 도구입니다. 흐름에 대한 제어가 차이가 있습니다. 프레임워크는 구현한 메소드가 프레임워크에 의해 호출됩니다. 라이브러리는 개발자가 필요한 시점에 직접 호출하여 사용합니다.
