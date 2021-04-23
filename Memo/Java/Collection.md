# 콜렉션 (Collection)

## List
```
  - 순서를 유지하면서 저장
  - 데이터의 중복 저장
```
`add()`, `set()`, `get()`, `remove()`로 처리

대표클래스 : 
 - ArrayList
 - Vector : `synchronized` 메서드 처리 멀티스레스상에서도 안전하다. 동시접근 허용하지 않는다. 그래서 처리 속도가 느리다
 - LinkedList : 삽입, 삭제에 용이함 (데이터의 추가, 삭제 시에 ArrayList처럼 불필요한 데이터의 복사가 없음. nextNode 위치만 알고 있음 되니까)
## Set
```
  - 순서를 유지하지 않고 저장
  - 중복 저장 불가
```
`get()` 메소드를 사용할 수 없음. (순서가 없으니까)
Collecion의 요소들을 읽기 위해 `Iterator`를 이용한다. `Interator.hasNext()`로 남았는지 확인, `next()`로 조회

대표 클래스 : 
 - HashSet
 - TreeSet : 검색에 용이한 데이터 구조

## Map
```
 - Key and Value로 저장하는 구조
```
Key가 중복되어서는 안됨

대표 클래스
 - HashMap
 - Hashtable : `synchronized` 메서드로 구성된 클래스
 - Properties : 이것을 이용하여 파일 입출력 등 데이터 관리를 할 수 있다


 과제 : Treeset을 이용한 Lotto 프로그램 만들기..
