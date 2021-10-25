# 면접 질문 스터디 - 7회차 (2021.10.25)

## JVM의 메모리 구조에 대해서 설명해 주세요. [참고](https://velog.io/@agugu95/자바와-JVM-그리고-메모리-구조)
> JVM은 Runtime Data Area, GC, Execution Engine, Class Loader로 구성되어 있습니다. 그 중에서도 메모리 영역인 Runtime Data Area는 메소드 영역, 힙 영역, 스택 영역, PC 레지스터, 네이티브 메소드 스택으로 이루어지며 또 힙은 eden, survivor1, survivor2, old, ~~permanent~~ 로 이루어집니다.

### 추가 설명
![사진1](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile22.uf.tistory.com%2Fimage%2F9973563D5ACE0315215FF6)  


힙 구조  
![사진2](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile4.uf.tistory.com%2Fimage%2F99AED1445ACE0E4B04A102)  

- Eden : new를 통해 생성된 객체. 살아남은 객체는 survivor1로 이동
- survivor1, 2 : 참조를 확인하여 GC 정리 실행. 살아남으면 survivor2 -> Old로 이동
- Old : mimor GC에서 살아남은 객체. Major GC에서 관리
- Permanent : Class Loader에 의해 Load된 클래스들이 저장. JDK 8부터는 Metaspace 영역으로 교체 [참고](https://goodgid.github.io/Java-8-JVM-Metaspace/)
- Metaspace : Native 메모리 영역에 위치. default 용량이 정해져있지 않아, 필요한 만큼 활용한다.

## Java Instrumentation이란 무엇인가요? [참고](https://bohemian-code.tistory.com/19)
> Byte Code Instrumentation이라 하여, 자바 코드는 건드리지 않고 바이트 코드를 수정하여 추가 기능을 제공하는 방법을 말합니다. 단순하게는 모니터링 기술에 활용되며, 궁극적으로는 AOP를 구현하기 위해 활용합니다.

### Byte Code Instrumentation
전통적인 기계어는 하드웨어/OS에 종속적이나, 자바 바이트 코드는 JVM에 종속적이기 때문에 어디에서든 동일한 구동이 가능하다. 직접 바이트코드를 수정할 수도 있으나 복잡하기 때문에, BCI를 지원하는 라이브러리를 활용한다. (ASM, BCEL, SERP, Javassist 등)

## Java의 synchronized Lock 범위에 대해서 알려주세요. (Class Lock, Instance Lock) [참고](https://jgrammer.tistory.com/entry/Java-혼동되는-synchronized-동기화-정리)
> 일반적인 synchronized Lock은 인스턴스 단위로 lock을 공유합니다. 그리고 static 메소드에 synchronized 를 활용하면 클래스 단위로 lock을 공유합니다. 필요하다면 block 단위로 lock을 지정할 수도 있습니다.

### 인스턴스 단위, 클래스 단위
- lock을 공유할 때 synchoronized 키워드가 적용된 곳에 대해 한정한다. 키워드가 없는 영역은 해당 없다.
- block 단위는 lock의 대상을 지정 및 범위를 한정시킬 수 있다.

### 클래스, 객체, 인스턴스 [참고](https://gmlwjd9405.github.io/2018/09/17/class-object-instance.html)


## 추상 클래스와 인터페이스의 차이는 무엇이고, 어느 때에 추상 클래스를 사용하고 인터페이스를 사용하실 건지 설명해주세요. [참고](https://velog.io/@new_wisdom/Java-추상-클래스와-인터페이스의-차이)
> 둘 다 선언에 대해 구현이 필요한 경우입니다. 다만 인터페이스는 변수가 `public static final`로 한정적이고, 추상 클래스는 더욱 여러 형태를 가질 수 있습니다. 추상 클래스는 공통적인 기능에 대해 추가적인 기능을 더할 때 사용하고, 인터페이스는 동일한 행동을 보장하기 위해 사용합니다.

### 사용 케이스
추상 클래스
 - 관련성이 높은 클래스 간에 코드를 공유할 때
 - 추상 클래스를 상속하는 클래스들이 공통 메소드, 필드가 많거나 public 이외 접근자가 필요한 경우
 - 각 인스턴스마다 상태를 지정하기 위한 필드가 필요한 경우
인터페이스
 - 서로 관련성이 없는 클래스를 여러개 구현할 때
 - 특정 데이터 타입의 행동은 명시하되, 구현은 신경쓰지 않는 경우
 - 다중 상속

## Bean이란 무엇인가요?
> 스프링 컨테이너에서 관리하는 객체를 빈이라고 합니다. 스프링 컨테이너에서 빈을 관리함으로써 DI와 IoC를 활용할 수 있습니다.
