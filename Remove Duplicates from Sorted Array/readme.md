# Remove Duplicates from Sorted Array
정렬된 배열에서 중복된 원소를 제거하기   
[문제 출처 링크](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

> Given a sorted array nums, remove the duplicates **in-place** such that each element appear only once and return the new length.   
> **Do not allocate extra space for another array**, you must do this by modifying the input array in-place with O(1) extra memory.


**in-place**의 의미는, 별도의 메모리 할당 없이 입력받은 리스트 내에서 정렬이 이뤄진다는 것을 말한다. 다른 배열을 위해 별도의 공간을 할당하지 말라고 조건에 한 번 더 명시되어 있다.   

-----

## 나의 풀이

### Javascript
Shift를 이용해 가장 앞의 원소와 그 다음원소끼리만 비교하며 순환시켰다.
리스트를 다시 정렬시키기 위해 첫 원소 값과 마지막 원소 값을 저장했으며, 탐색 중단점을 찾기 위해 count 변수를 사용했다.

배열 정리가 뒤틀려도 갯수만 맞으면 답으로 간주할 것 같았는데, run code에서는 틀린 것으로 간주했다. 따라서 다시 재정렬하는 과정이 들어갔고, 더 지저분해졌다..

결과적으로 하위 30%의 기록으로, 아직도 많이 멀었다는 생각이 든다. 

### Javascript 모범답안
```javascript
var removeDuplicates = function(nums) {
    let index = nums.length;            // 입력받은 배열의 길이만큼 수행
    while(index > 0){
        let lastElement = nums.shift(); // 맨 앞 원소를 리스트에서 제거, 저장

        if (nums.indexOf(lastElement) == -1) {
            // nums에서 lastElement와 같은 원소를 찾지 못한다면
            nums.push(lastElement);     // 해당 원소를 다시 push
        }
        // 만약 위의 if문에서 같은 원소 값을 찾았다면, 뽑아낸 lastElement는 중복이므로
        // push하지 않고 제거

        index--;                        // 원소의 갯수만큼 반복하도록 index--
    }
};
```
length 만큼 반복문을 실행하여 해결할 수 있을만큼 간단해졌다. 별도의 count로 정지하는데다, 정렬을 다시 실시해야하는 나의 코드보다 훨씬 간단하고 가독성도 좋다.
머릿 속으로 한 번 더 정리하고 하는 연습을 해야겠다..

### Java
Java에서는 length만 구하는 것이라면 엄청 간단하게 해결할 수 있는 문제다. 다만, nums를 중복 요소를 제거한 배열로 만들기 위해 
추가적으로 확인한 사항이 있다.

Java의 경우, C/C++과 같은 개념의 포인터는 없다. [참고](https://mdown.blog.me/221316604612) 그리고 배열의 공간을 변경하기 위해서는 새로운 공간을 할당하고, 값을 복사하는 식으로 이루어져야한다.   

배열 안에서 원소를 삭제하고, 이어 붙이는 방식을 원하는데, 이러한 방식이 어려울 것이라 생각했다. javascript의 모범답안을 참고하여, 중복되지 않은 원소 갯수를 count하고, 해당 count만큼 ```int[]```를 새로 할당하여 답안을 제출했다. ```Arrays.copyOf(nums, count)```는 크기를 재할당하면서, 배열의 끝부분을 잘라내 배열 크기를 줄일 수 있는 것 같다. [참고](https://stackoverrun.com/ko/q/2090915)

속도는 나아졌다 치더라도, 메모리는 너무 많이 할당한 듯 하다..



------

풀이일자 : 2020.07.20    
사용 언어 : 
 - Javascript 성공   
  (시간 : 152 ms (상위 67%), 메모리 : 39.1 MB (상위 71%))
 - Java 성공   
  (시간 : 1 ms (상위 55%), 메모리 : 45.3 MB (상위 95%))
 - 
