# 면접 질문 스터디 - 4주차

## Java 8 버전에 추가된 중요 기능들에 대해 설명해주세요.
> 대표적으로 람다식, 스트림, 옵셔널, 날짜 클래스 추가, 디폴트 메소드가 있습니다.

### Java 8 추가 기능 [참고](https://medium.com/@inhyuck/java-8에-추가된-것들-8c66023cbbae)
- Lambda 표현식 : 람다 표현식으로 함수를 표현할 수 있다. 코드의 가독성이 높아진다.
- Stream : 연속된 정보를 처리하기 위해 사용. (`filter()`, `map()`, `forEach()`, `reduce()` 등)
- 함수형 인터페이스
- Optional : null 처리를 간편하게 할 수 있도록 지원 (`get()`, `orElseThrow()`)
- Default Method : 인터페이스 내에 구현된 메소드를 추가해야할 때 `default` 키워드를 붙여 활용한다. (하위 호환성, 람다식을 위해 지원)
> 스터디원 공유하다보니, `static`도 활용할 수 있다! [참고](https://dahyeee.tistory.com/entry/JAVA-interface-default-static%EB%A9%94%EC%86%8C%EB%93%9C)
- 날짜 관련 클래스 추가 : `LocalDate`, `ZoneDateTime`, `DateTimeFormatter`, `DayOfWeek`
- 병렬 배열 정렬 : `parallelSort()` 지원
- StringJoiner : 문자열 처리 클래스 `StringJoiner` 추가

### Arrays.parallelSort()
자바 8부터 제공하는 API. 배열을 작은 단위로 쪼개어 정렬한 다음 다시 조합하는 방식이다. 멀티스레드로 동작하기 때문에 일반적인 Array.sort()보다 빠르다.

## Java의 함수형 인터페이스는 무엇인가요? [참고](https://codechacha.com/ko/java8-functional-interface/)
> 1개의 추상메소드를 갖는 인터페이스를 말합니다. 람다식에서 활용할 수 있도록 제공합니다. 기본적으로 제공하는 함수형 인터페이스에는 `Runnable`, `Supplier`, `Consumer`, `Function<T, R>`, `Predicate` 등이 있습니다.

### 예시
```java
public interface FunctionalInterface {
     public abstract void doSomething(String text);
}

FunctionalInterface func = text -> System.out.println(text);
func.doSomething("do something");
// 실행 결과
// do something
```

## Array와 ArrayList의 차이점은 무엇인가요? [참고](https://velog.io/@adam2/Array%EC%99%80-List그리고-Java-List)
> Array는 기존의 배열을 생각하면 되겠습니다. 보통 고정적인 공간을 할당하여 활용하기 때문에 빈공간을 가질 수 있고, 저장 순서와 인덱스가 일치하는 형태입니다.  
> ArrayList는 List 구현체의 하나로서, 인덱스로 객체를 관리하지만 크리를 동적으로 관리할 수 있는 형태입니다. 그리고 원시형 변수는 이를 통해 관리할 수 없습니다.  
> 추가/삭제에서는 List가 유리하며, 조회에서는 Array가 유리합니다.

### ArrayList
처음 내부에서 설정한 저장 용량(capacity)가 있고, 이를 초과할 경우 1.5배로 증가시킨다. 또한 중간의 객체가 제거될 경우, 인덱스를 유지하기 위해 데이터의 이동이 발생한다.

잦은 원소의 추가/삭제에 대해서는 다른 List 구현체를 이용하는 것이 유리하다.

## 생성자 주입은 빈 생성 때 사용되는 리플랙션 외에 추가적인 리플랙션을 진행하나요?
> @Autowired 과정에서 해당 클래스에 해당되는 정보를 가져와서 활용한다 정도로 이해했습니다. ㅠㅠ 

### ??
[참고](https://better-dev.netlify.app/java/2020/08/15/thejava_7/)  
[참고](https://taes-k.github.io/2021/05/23/spring-di-reflection/)

## Monolithic Architecture, Micro Service Architecture에 대해 각각 설명해 주세요.
> 모노리틱 아키텍쳐(Monolithic Architecture)는 전통적인 웹 시스템 개발 스타일로, 하나의 애플리케이션 안에 모든 로직이 포함된 형태입니다.
> 마이크로 서비스 아키텍쳐(Micro Service Architecture)는 대용량 웹 서비스를 처리하기 위해 고안된 아키텍쳐입니다. 각 서비스가 API를 통해 정보를 교환하며 작업이 이루어집니다. 여러 대의 서버로 분산하여 처리할 수 있으나, 유지보수에 대한 난이도가 높아집니다. (테스트 어려움, 시간 및 메모리 소요 증가, 트랜젝션 문제)

### 모노리틱 아키텍쳐 [참고](https://developer-jp.tistory.com/6)
![사진](https://t1.daumcdn.net/cfile/tistory/9963B93359A3CE1627)  
모든 로직이 하나의 애플리케이션에 포함된 형태. 하나의 애플리케이션에서 처리하기 때문에 배포, 테스트가 간편하다. 하지만 대형 프로젝트의 경우 단일 크기가 커져서 빌드 및 배포 소요 시간, 서버 기동 시간이 증가하며 하나의 오류로도 빌드 실패를 유발할 수 있어 협업 난이도가 올라간다.

### 마이크로 서비스 아키텍쳐 [참고](https://developer-jp.tistory.com/7)
![사진](https://t1.daumcdn.net/cfile/tistory/99AF813359A3D84020)  
여러 서비스가 API의 형태로 자료를 교환하며 작업하는 형태입니다. 서비스별로 별도의 데이터베이스를 이용할 수 있는 등 대용량 처리에 유리하나, 트랜젝션 문제가 발생할 수 있습니다.  
또한 각 서비스별로 서버가 다르다보면, 서버 URL이 달라져 사용하기 어려운 문제가 있습니다. 이를 해결하기 위해 **API Gateway**를 활용합니다.
