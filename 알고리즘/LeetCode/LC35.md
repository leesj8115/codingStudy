# Search Insert Position 
[문제 출처 링크](https://leetcode.com/problems/search-insert-position/)

> Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.   
> 
> You may assume no duplicates in the array.

정렬된 배열 nums에서 target이 순서상 어디에 들어가야할지 index를 반환하는 문제이다.
해당 값과 동일하다면 해당 위치를, 같은 값이 없다면 대소를 판단하여 자리를 정하여야 한다.

-----

## 나의 풀이

### Javascript
```javascript
var searchInsert = function(nums, target) {
    var i = nums.indexOf(target);       // indexOf 함수로 해당 값이 있는지 확인
    if (i !== -1)
        return i;       // 있다면 바로 반환

    else if (target < nums[0])
        return 0;       // 처음 값보다 작다면 첫 위치 반환

    else if (target > nums[nums.length-1])
        return nums.length;     // 가장 큰 값보다 크다면 문자열 길이 (맨 끝 위치) 반환


    // target이 사이값이라면, 대소를 비교
    for (i = 0; i < nums.length-1; i++) {
        if ((nums[i] < target) && (nums[i+1] > target))
            return i+1;
    }

};
```
`indexof()` 함수를 이용해서 먼저 값을 탐색했다. 이후 사잇값은 별도의 비교를 통해 계산했다. `indexOf()`를 사용하지 않고 nums의 길이만큼 for문을 통해 해결하고 싶었으나, 예외사항을 별도의 if문으로 처리하는 것이 간단하여 이렇게 실시했다.

### Javascript 모범답안
```javascript
var searchInsert = function(nums, target) {
    let i = 0;
    while (nums[i] < target) i++;

    return i;
};
```
그리 어렵지 않았다. 말그대로 **큰지 작은지**에 대한 판단만 실시해주면 되었기에, 이렇게 간단한 코드가 있다. `indexOf()` 함수를 너무 맹신하며 사용하게 된다. 간단한 로직이라면 더욱 고민하여 작성해야겠다.

### Java
이번에도 javascript의 모범답안을 보고 로직을 동일하게 반영할 수 있어, 모범 답안만 첨부한다.

### Java 모범답안
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int p = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target)
                p++;
        }
        
        return p;
    }
}
```
javascript의 모범답안과 동일하지만, `nums = [1, 3, 5, 6] , target = 7`일 경우 
javascript의 모범답안을 그대로 반영하게되면 out of index가 발생한다. 그래서 반복문의 범위를 nums의 길이만큼으로 한정하여 실시했다.

------

풀이일자 : 2020.08.10
사용 언어 : 
- Javascript 성공 (시간 : 72 ms, 메모리 : 37 MB)
- Java 미실시
