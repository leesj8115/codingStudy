# Remove Element 
[문제 출처 링크](https://leetcode.com/problems/remove-element/)

> Given an array nums and a value val, remove all instances of that value in-place and return the new length.

> Do not allocate extra space for another array, you must do this by **modifying the input array in-place** with O(1) extra memory.

> The order of elements can be changed. It doesn't matter what you leave beyond the new length.

입력으로 받은 배열 nums에서 val을 제외한 배열의 길이를 반환하기. 배열 중간에 있는 원소를 어떻게 제거할 것인지가 관건이며, 지난 문제와 동일하게 **in-place** 환경 내에서 해결해야 한다.

-----

## 나의 풀이

### Javascript
Javascript에서는 배열 내 원소를 제거할 때 ```splice()``` 함수를 사용할 수 있다. val로 입력받은 원소에 대해 ```indexOf()```로 찾아내고, 해당 위치에 대해 ```splice()```를 사용했다.

답안 코드도 깔끔하게 나왔지만, 기록이 저조한 것이 아쉽다..
특히 메모리에서 소요가 컸다.

### Javascript 모범답안
```javascript
var removeElement = function(nums, val) {
    let i = 0;
    while(i < nums.length) {
        if(val - nums[i] === 0) {
            nums.splice(i, 1);
        }
        else {
            i += 1;
        }
    }
    return nums.length;
};
```
```indexOf()```을 통해 원소를 찾기 보다, 배열에 직접 접근하여 원소를 비교했다. 이 코드는 48ms를 기록했다. 모범답안을 보기 전에, 속도가 느렸던 이유는 ```splice()```를 사용했기 때문이라고 생각했는데, 그 부분이 문제는 아니었다. 배열의 길이가 정해져있다면 ```indexOf()```보다는 직접탐색으로 활용하는 것이 좋겠다.

### Java
Java에서는 원본 배열의 훼손이 일어나도, val의 값을 제외한 length가 반환되면 해결이라 생각했다. 하지만 원본 배열도 val을 제외한 나머지 원소는 온전한 형태로 돌아와야했다.

여기서 고민할 점은, in-place이므로 val에 값에 해당하는 자리에 어떻게 val이 아닌 값을 입력할 수 있을지 고민이다. 이 고민을 하던 중에 답을 확인해서, 모범답안만 첨부한다.

### Java 모범답안
```java
public int removeElement(int[] nums, int val) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
}
```
답은 **포인터를 2개 운용**하는 것이었다. 하지만 이것이 완벽한 답이 아니라고 생각한다. val이 아닌 원소를 앞으로 당겨서 정리만 하는 것이 옳은 답인가? 싶고, 문제에서 제공된 예제 2인 ```nums =  [0, 1, 2, 2, 3, 0, 4, 2]; val = 2;```를 실행했을 때, 결과 배열로 ```[0, 1, 3, 0, 4, 0, 4, 2]```가 반환된다. ```length=5``` 이내로는 완벽한 답안이지만, 배열에 여전히 val이 남아있다.
메모리 크기를 변경하기 위해서는 새로 할당해야하는 Java의 특성상 in-place 내에서는 이것이 최선인듯 하다.

------

풀이일자 : 2020.07.21    
사용 언어 : 
- Javascript 성공 (시간 : 84 ms (상위 74%), 메모리 : 37.1 MB (상위 95%))
- Java 미실시
