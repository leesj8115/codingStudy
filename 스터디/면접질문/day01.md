# 면접 질문 스터디 - 1주차

## Garbage Collector은 무엇이고, Parallel GC와 CMS GC, G1 GC의 큰 차이는 무엇인지 설명해주세요. (mark-sweep-compact, concurrency-sweep, garbage-first)

### Garbage Collector
가비지는 '정리되지 않은 메모리', '유효하지 않은 메모리 주소'다. 가비지 컬랙터는 이러한 필요없는 메모리 영역을 반환하는 메모리 관리 기법이다.

장점으로는 메모리의 관리가 보다 편리해지며, 여러 버그(유효하지 않은 포인터 접근, 이중 해제(반환된 메모리를 다시 반환하려는 시도), 메모리 누수)를 줄일 수 있다. 하지만 단점으로 직접 관리하지 못했을 때의 단점이 따라온다. (어떤 것을 언제 반환할지에 대한 계산 비용, 가비지 콜랙터가 언제 반환하고 언제까지 점유하는지)

### Parallel GC와 CMS GC, G1 GC [참고](https://d2.naver.com/helloworld/1329)
가비지 콜랙터의 방식에 따라 처리 절차가 달라진다. Parallel GC와 CMS GC, G1 GC는 각 처리 방식을 말하는데, 다음과 같다.
- Serial GC : `mark-sweep-compact` 알고리즘을 활용한다. 단일 처리 스레드로 작동하기 때문에, 제한된 환경(적은 메모리, 적은 CPU 코어)에 적합하다.
- **Parallel GC** : 시리얼 방식과 알고리즘은 같으나, GC 처리 스레드가 여러개이다.
- Concurrent Mark & Sweep GC(**CMS GC**) : `Tri-color Marking` 알고리즘을 활용한다. 'stop-the-world' 가 발생하는 시간을 줄여서 적은 레이턴시가 필요한 작업에 활용한다. 하지만 다른 방식보다 메모리와 CPU를 더 활용한다. 그리고 Compation 단계(조각모음)이 이뤄지지 않는다.
- G1 GC : stop-the-wolrd를 최소화 하고 처리량도 확보하기 위해 가져온 방식이다. 앞서 확인했던 JVM의 힙 메모리 구조를 벗어나, 모든 영역을 일정 크기의 **Region** 으로 나눈다. 이후 필요할 경우에 young 영역, old 영역으로 할당하여 처리하고, 반환시 다시 Free region이 된다. 이 방식을 통해 각 region들을 관리하고, 각 region을 기준으로 공간이 꽉 찬다면 쓰레기는 제거하고, 생존한 객체는 다른 영역으로 이동한다.

### GC의 처리 방법 알고리즘
GC의 종류가 방식에 따라 나뉘어진다고 했다. 그 방식들은 다음과 같은 알고리즘을 활용한다.
- `mark-sweep-compact` [참고](https://medium.com/@joongwon/jvm-garbage-collection-algorithms-3869b7b0aa6f) : 각 객체에 대해 **Mark** 작업을 하여 메모리 반환 대상을 구분한다.(이 때 Flag나 BitmapTable을 활용하곤 한다.) 이 과정에서 분류된 대상에 대해 **Sweep** 과정을 거친다. 이후에 다른 메모리가 할당하지 못하는 Fragmentation 상황을 방지하기 위해 **compact** 과정을 실행한다. 힙 메모리를 효율적으로 사용하기 위해 객체들을 앞쪽으로 모으는 과정을 거친다.
- `Tri-color Marking` [참고](https://perfectacle.github.io/2019/05/11/jvm-gc-advanced/) : 회색(처리가 되지 않은 객체), 검은색(모든 처리를 마친 객체), 흰색(수집 대상이 되는 객체)로 나누어 처리하는 과정이다. 회색 객체부터 출발하여, 참조 과정에 있는 객체를 회색으로 만든다. 그리고 참조 대상이 없을 경우 자신을 검은 색으로 만든다. 이후 모든 회색 객체가 작동을 완료했을 때, 참조가 된 객체는 검은색, 참조가 되지 않은 객체는 흰색으로 표시된다. 흰색 객체만 메모리를 해제하여 정리하는 방식이다.

### GC 방식의 차이는?
GC 방식에서 차이가 나는 이유는, 각 상황에 맞게 효율적인 GC를 사용하기 위함이 1차적인 목표이며 객체/쓰레기를 구분하는 과정은 모두가 소요되나 어떻게 하면 stop-the-world를 효율적으로 다룰 것인가에 대한 차이가 크다.

### Young 영역, Old 영역
JVM에서는 메모리를 Young 영역(새로 생성한 객체들이 있다가 사라지는 곳. Minor GC 작동), Old 영역(Young 영역에서 살아남은 객체. Major GC 작동)으로 나누어 처리한다.
- Young 영역 : Eden 영역과 Survivor 영역 2개로 이뤄져있다. 에덴 영역에 최초 객체가 만들어지고 Servivor 영역을 통해 Old 영역으로 이동한다.
- Old 영역 : Young에서 살아남은 객체가 저장되는 곳. 여기서는 GC의 방식에 따라 처리된다.

### stop-the-world (STW)
GC를 실행하기 위해 JVM이 모든 애플리케이션 실행을 멈추는 것. stop-the-world 발생시 GC를 실행하는 스레드를 제외한 나머지 스레드가 작업을 멈춘다.

-----

## atomic Type과 CAS는 무엇이고 언제 사용되는 것인가요? [참고](https://readystory.tistory.com/53)
멀티 스레드 환경에서 작업을 하다보면, **경쟁 상태(race condition)**를 고려해야 하는데, 이를 해결하기 위한 방법으로 atomic type, volatile, synchronyzed 를 활용한다. 이중에 동시성 문제를 해결하고자 atomic type을 활용한다.
- atomic type : CAS(Compare-And-Set. 변수의 값을 변경하기 전에 기존에 가지고 있던 값이 내가 예상하던 값과 같을 경우에만 새로운 값으로 할당하는 방법) 방식을 이용한 방법. 기존의 값과 메소드 처리시의 값을 비교하여, 일치할 때만 처리하는 방식으로 동시성 문제를 피한다.
- volatile : 일반 변수는 메모리의 값을 캐시에 저장해서 활용한다. 하지만 각 스레드가 변수를 읽을 때 캐시에 저장된 값이 달라 문제가 생긴다. 이를 해결하고자 변수에 `volatile`을 붙여 캐시를 활용하지 않고, 메모리에서 값을 읽고 쓰도록 한다. 이를 통해 **읽을 때 항상 최신의 값을 보장**하면서 가시성을 보장하나, 동시성을 보장하지는 않는다..
- synchronized : 쓰기가 빈번한 공유 데이터를 활용할 때는, 여러 스레드가 동시에 사용할 수 없도록 lock을 활용한다. 다른 스레드의 접근을 원천차단하지만, 비용이 크다.

## Interceptor와 Filter의 차이점을 말해주세요. [출처](https://mangkyu.tistory.com/173)

### 필터(Filter)
필터는 J2EE표준 스펙 기능으로 디스패처 서블릿(Dispatcher Servlet)에 요청이 전달되기 전/후에 url 패턴에 맞는 모든 요청에 대해 부가작업을 처리할 수 있는 기능을 제공한다.

![필터](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbZQx9K%2Fbtq9zEBsJ75%2FdEAKj1HEymcKyZGZNOiA80%2Fimg.png)

`javax.servlet`의 Filter 인터페이스를 구현해서 활용하며, `init()`, `doFilter()`, `destroy()` 메소드를 갖고 있다.
- init() : 필터 객체 생성
- doFilter() : 필터에서 처리할 공통 작업 등의 코드 처리 담당
- destroy() : 필터 객체 반환

### 인터셉터(Interceptor)
필터와 달리 스프링이 제공하는 기능으로, 디스패처 서블릿(Dispatcher Servlet)이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공한다.

![인터셉터](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSz6DV%2Fbtq9zjRpUGv%2F68Fw4fZtDwaNCZiCFx57oK%2Fimg.png)

`org.springframework.web.servlet`의 HandlerInterceptor 인터베이스를 구현하며 `preHandle()`, `postHandle()`, `afterCompletion()` 메소드를 갖고 있다.
- preHandle() : 세부 컨트롤러 호출 전에 실행. (전처리 혹은 요청 정보 가공)
- postHandle() : 세부 컨트롤러 호출 후 실행. (후처리)
- afterCompletion() : 모든 작업이 완료(뷰에서 최종 결과를 보여주는것 포함)됐을 때 실행. 일부 리소스 반환시 사용

### 필터와 인터셉터의 차이점
![차이점](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F2Mdrx%2Fbtq9EixbYNA%2FteKTnL0zHorzCZ64hsgDkK%2Fimg.png)  
처리되는 시점이 다르기 때문에, 이에서 오는 차이점이 크다.

필터는 디스패처 서블릿보다 먼저 처리되는 만큼, 전역에서 **모든 작업에 대한 검사/보안**과 인코딩 작업 등에 활용된다.

인터셉터는 스프링 내부에서 **세부 컨트롤러의 작업과 연계한 요청/리소스 정리**에 활용한다.

### 디스패처 서블릿 [참고](https://mangkyu.tistory.com/18)
가장 앞단에서 HTTP 프로토콜로 들어오는 모든 요청을 가장 먼저 받아 적합한 컨트롤러에 위임해주는 프론트 컨트롤러. 공통 작업을 처리한 후 해당 요청에 대한 세부 컨트롤러를 통해 작업한다.

## Lock Striping은 무엇이고 어떠한 자료구조가 해당 방식을 구현하였나요? [참고](http://egloos.zum.com/Agbird/v/4849046)
Lock Striping 방법은 synchronized를 통해 메소드 전체에 락을 거는 방법과 달리, 자료구조 내부에 여러 세그먼트를 두어 그 **세그먼트 별로 락을 거는 방식**을 말한다. 같은 객체에 동시에 접근하더라도, 세그먼트가 겹치지 않는다면 경쟁하지 않는다.

이러한 방식을 통해 구현된 자료형은 `Concurrent...`로 시작하는 형태를 갖고 있다.  
![목록](https://raw.githubusercontent.com/leesj8115/codingStudy/master/%EC%8A%A4%ED%84%B0%EB%94%94/%EB%A9%B4%EC%A0%91%EC%A7%88%EB%AC%B8/img/Concurrent_list.png)


## VO, DTO, DAO에 대해서 각각 설명해 주세요.

### Data Access Object(DAO)
데이터베이스에 실질적으로 접근하는 객체. DB에 매번 접근하기 위해 커넥션 객체를 생성하는데, ConnectionPool을 통해 미리 커넥션 객체를 만들어놓고 사용한다. 이를 위해 데이터베이스에 접근하는 객체를 별도로 만들어 담당하도록 한다.

### Data Transfer Object (DTO)
계층간 데이터 교환을 위한 객체. 각 계층별로 필요한 데이터로 변환하기 위해 DTO를 활용하며, 별도의 처리보다 getter, setter를 통한 데이터 교환에 활용된다.

### Value Object (VO)
값 오브젝트로서 활용한다. 불변으로서 활용하기 때문에, readOnly 특징을 갖는다. DTO와 혼용해서 사용하곤 한다.

