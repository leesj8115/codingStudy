# 면접 질문 스터디 - 3주차

## Shallow Copy와 Deep Copy의 차이는 무엇인가요? 자바에서 Deep Copy를 하기 위해서는 무엇을 사용하여야 하나요?
> 얕은 복사(Shallow Copy)는 메모리에 있는 주소 값만을 복사해 활용합니다. 객체는 1개로 동일하기 때문에, 값의 변경시 양 쪽에서 같은 결과를 조회합니다. 반면에 깊은 복사(Deep Copy)는 해당 주소에 있는 객체를 복사하여 다른 메모리 주소를 참조합니다. 깊은 복사로 생성된 객체는 이전 객체와 독립적으로 활용합니다.
> 깊은 복사를 하기 위해서는 직접 새로운 객체를 만들어 대입하거나, `clone()` 메소드를 활용할 수도 있는데 이는 깊은 복사를 위한 메소드를 오버라이딩 해야합니다.

### clone [참고](https://plas.tistory.com/20)
인터페이스 `Cloneable`에 정의된 `clone()`을 오버라이딩하여 활용한다.

## Fail-fast iterator는 무엇이고 어떤 것을 위해 사용되는 건가요? [참고](https://simuing.tistory.com/entry/JAVA-Fail-Safe-Iterator-vs-Fail-Fast-Iterator) [참고2](https://118k.tistory.com/656)
> Fail-fast 방식과 Fast-safe 방식을 비교하여 이해해야합니다. **Fail-fast**는 실패를 조기에 확인하고 작업을 중단하는 방식이며, **Fast-safe**는 안전성을 우선으로 하여, 실패를 피하고자 노력하는 방식입니다. Fail-fast Iterator는 탐색 중에 콜렉션 데이터의 변화를 감지하면, 익셉션을 발생시킵니다. 이를 통해 데이터를 순회하는데 발생하는 문제를 조기에 확인하고 처리할 수 있도록 합니다.

### Fail-Fast Iterators
 - java.util 패키지 콜렉션의 기본 반복자 형태. (ex ArrayList, HashMap)
 - `Iterator.remove()` 이외의 코드로 콜렉션 데이터가 수정될 시 예외 발생(`ConcurrentModificationException`)

### Fail-Safe Iterators
 - java.util.concurrent 패키지 콜렉션의 기본 반복자 형태. (ex ConcurrentHashMap, CopyOnWriteArrayList)
 - 콜렉션 데이터의 복제본을 만들어 작업을 실행. 원본 데이터가 수정되더라도 작업은 유지
 - 복사본을 보고 작업하기 때문에, 업데이트 된 데이터를 처리하지 않는다.
 - 또한 복사본을 만드는데 오버헤드 (자원 소모)가 발생한다.

## 재정의된 equals와 ==의 차이는 무엇인가요? [참고](https://donghyeon.dev/이펙티브자바/2021/01/04/eqauls를-재정의-하는-방법)
> equals를 사용하는 경우는, ==(비교 연산자)에서 제공하는 물리적 주소의 비교가 아닌, 데이터 값이 같은지를 비교하기 위해 사용합니다. 이를 재정의하여 사용한다는 것은 클래스 등 포함한 데이터에 대한 비교를 모두 한다는 이야기이며, 이는 ==와 달리 같은 곳을 참조하지 않더라도 같은 데이터를 갖고 있어 true를 반환할 수 있습니다.  
> 물론 꼭 equals를 재정의할 필요는 없습니다. 각 상황에 맞게 재정의하여 사용하면 되고, 재정의하더라도 반사성, 대칭성, 추이성, 일관성, not-null에 대해 고려하며 작성해야합니다.

### equals 재정의 규약
- **반사성(reflexivity)** : null이 아닌 참조 값 x에 대해 자기 자신을 참조하면 true다. `x.equals(x) = true`
- **대칭성(symmetry)** : null이 아닌 참조 값 x, y에 대해 true일 경우 서로 대칭한다.
`x.equals(y) = true`이면 `y.equals(x) = true`이다.
- **추이성(transitivity)** : null이 아닌 모든 참조 값 x,y,z에 대해 삼단 논법이 적용된다. `x.equals(y) = true`이고, `y.equals(z) = true`면, `x.equals(z) = true`이다.
 - **일관성(consistency)** : null이 아닌 모든 참조 값 x,y에 대해 결과가 일정하다. `x.equals(y)`를 반복해서 호출했을 때 값은 일정하다. (true or false)
 - **not-null** : null이 아닌 모든 참조 값 x에 대해 null과 비교는 false다. `x.equals(null) = false`

## ConcurrentHashMap은 어떤 방식으로 스레드 동시성을 보장하나요? [참고](https://devlog-wjdrbs96.tistory.com/269)
> ConcurrentHashMap은 Hashtable의 단점을 보완하면서 멀티 스레드 환경에서 활용할 수 있도록 나온 클래스입니다. 모든 메소드에 `synchronized`가 붙은 Hashtable과 달리, ConcurrentHashMap은 읽기 작업은 여럿이 사용할 수 있도록 제공하고, 쓰기 작업에서는 버킷 단위로 락을 걸어 처리합니다. 모든 자원에 락을 거는 방식에 비해 세그먼트로 나누어진 구조에서 일부만 락을 걸기 때문에, 동시 처리가 더욱 용이해집니다.


## 스프링에서 Bean으로 Filter를 구현할 수 있을까요? 혹시나 가능하다면 어떻게 할 수 있을까요? [참고](https://gardeny.tistory.com/35)
> 필터는 서블릿 단위에서 실행됩니다. 필터에서는 이후 작업을 위한 데이터 처리, 보안적인 요소로서 활용합니다. 스프링에서도 필터를 구현하여 활용할 수 있는데, `Filter` 인터페이스를 구현한 후 해당 클래스를 빈으로 등록하여 활용합니다.

### 흐름도 [참고](https://goddaehee.tistory.com/154)
![흐름도](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile22.uf.tistory.com%2Fimage%2F9983FB455BB4E5D30C7E10)  

