# 면접 질문 스터디 - 5주차

## Managed - Unmanaged 언어의 차이는 무엇이고 어떤 장, 단점이 있나요? [참고](https://dongkey2183.tistory.com/27) [참고2](https://algorfati.tistory.com/113)
> Heap 메모리 영역에 대해 프로그래머가 직접 관리할 경우 Managed 언어, 아닐 경우 Unmanaged 언어입니다.  
> Managed 언어는 C#, Java가 있으며 인터프리터, VM을 지원하며 해당 언어에서 메모리 관리를 지원합니다.
> Unmanaged 언어는 C, C++이 대표적이며 일반적으로 속도가 빠르고, 메모리 관리를 직접하기 때문에 자유도가 높습니다. 단점으로는 메모리 누수, 메모리 단편화 등의 문제를 직접 관리하며 해결해야 합니다.  

![사진](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2F2vLp1%2Fbtq453EvSAB%2FxRY7P59hzeUrMLKhTf4aw1%2Fimg.png)

### 메모리 단편화 [참고](https://jeong-pro.tistory.com/91)
메모리 단편화는 메모리의 공간이 작은 조각으로 나뉘어져 **사용가능한 용량이 있으나 할당이 불가능한 상태**를 말한다.
- 내부 단편화 : 프로세스가 필요한 양보다 더 많은 메모리를 할당했을 때
- 외부 단편화 : 사용하지 않는 메모리 공간이 조각조각 나뉘어 있어, 큰 메모리를 할당하지 못할 때

### GC의 유무가 차이점이 되서는 안된다? [참고](https://blog.seulgi.kim/2019/04/managed-language-vs-unmanaged-langauge.html)
GC가 메모리를 자동으로 관리하는 유일한 방법이 아니기 때문에, GC의 유무가 Managed - Unmanaged의 차이가되어서는 안된다.

## Java는 Call By Value일까요, Call By Reference 일까요? [참고](https://deveric.tistory.com/92)
> 자바는 Call By Value만 존재합니다. Call By Reference로 혼동할 수 있으나, 참조되는 대상에 대한 주소값을 복사하여 활용하기 때문에 Call By Value 입니다.


## 자바의 synchronized 키워드에 대해 설명해주시고 Reentrant Lock와의 차이는 무엇인지 말씀해주세요. [참고](https://eskeptor.tistory.com/83)
> 자바의 `synchronized`는 메소드에 락을 걸어 동기화 문제를 해결하는 형태입니다. `ReentrantLock` 클래스를 활용하면 동기화의 시작, 끝을 수동으로 명시하여 처리가 가능합니다.

```java
import java.util.concurrent.locks.ReentrantLock;

class SomeClass {
  private final ReentrantLock locker = new ReentrantLock();
  
  public void SomeMethod () {
    locker.lock(); // 쓰레드에 락을 겁니다.(동기화의 시작)
    
    try { 
      // 동기화내용들... 
    } catch (어떤예외들) {
      해당예외처리... 
    } finally {
      locker.unlock(); // 쓰레드의 락을 풉니다.(동기화 끝지점) 
    }
  }
}
```

### ReentrantLock
동기화 처리를 위한 방법. synchronized와 유사하나, 동기화 시작, 끝 지점을 코드로 지정할 수 있다.메소드에 약간의 차이가 있다.
- await() == wait()
- signal() == notify()
- signalAll() == notifyAll()

## Spring Boot가 해결하려고 했던 문제는 무엇이고 어떻게 해결하였나요? [참고](https://sas-study.tistory.com/274)
> 스프링은 관점 지향 프로그래밍(AOP) 시점에서, 기존 로직은 손을 대지 않고 추가할 수 있는 등 결합도를 낮추는 개발 방법을 제공했습니다. 하지만 각 라이브러리간에 호환되는 버전을 설정하거나, 최초 설정하는 불필요한 시간이 소요됐습니다.
이를 개선하고자 스프링부트는 자동설정을 통해 셋팅에 필요한 소요를 줄이고, 복잡도를 낮췄습니다.

## Reflection API는 Runtime에서 코드를 생성해내는데 많이 사용되게 됩니다. 이는 스프링에서도 적용되는데요. 스프링 컨테이너는 이런 Reflection으로 생성된 Bean을 알고 있나요? 어떻게 알 수 있을까요?
> 리플렉션을 통해 생성된 객체를 컨테이너에 등록하여, 해당 컨테이너안에 등록된 빈의 이름을 통해 조회하고 활용합니다.

### BeanFactory [참고](https://milenote.tistory.com/53)
BeanFactory 인터페이스를 구현한 DefaultListableBeanFactory 클래스를 활용하여 빈을 등록하고 관리한다. 해당 클래스 오브젝트를 통해 빈의 이름을 가져올 수 있고, 이를 통해 해당 빈과 정보 등을 조회할 수 있다.

### AnnotationConfigApplicationContext
이를 통해서도 빈을 가져올 수 있다.??
