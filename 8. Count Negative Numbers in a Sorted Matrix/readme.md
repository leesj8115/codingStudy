# Count Negative Numbers in a Sorted Matrix
[문제 출처 링크](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/)

> Given a `m * n` matrix `grid` which is sorted in non-increasing order both row-wise and column-wise.    
>
> Return the number of **negative** numbers in `grid`.

m * n 크기의 grid는 내림차순으로 정렬되었으며, 가로와 세로 모두에 적용된 규칙이다. 이 중 음수를 가진 원소의 갯수를 반환하면 된다. 어떻게 시간을 절약해서 풀 수 있는지가 관건이다.

-----

## 나의 풀이

### Javascript
```javascript
/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function(grid) {
    var result = 0;             // 결과 갯수
    var m = grid.length;     // rows 원소
    var n = grid[0].length;  // cols원소 갯수

    // 가장 기본적인 방법으로 탐색
    for (var i = 0; i < m; i++) {
        for (var k = 0; k < n; k++) {
            // 한 줄씩 비교하여, cols의 원소가 음수라면 이후 나올 cols도 음수로 판단
            if (grid[i][k] < 0) {
                result += n - k;
                break;
            }
        }
    }

    return result;
};

var input = [[4,3,2,-1], [3,2,1,-1], [1,1,-1,-2], [-1,-1,-2,-3]];
console.log("result = " + countNegatives(input)); 
```

누구나 생각할 수 있는 방법으로 문제를 해결했다. 2중 for문으로 탐색한다는 것인데, 이 방법의 단점은 row나 col 모두 내림차순으로 적용되었다는 점을 응용하지 못한다는 것이다.
cols간의 관계에 대해서만 내림차순을 적용했고, rows에 대한 관계는 코드에 반영되어있지 않다. 때문에 탐색 시간이 많이 소요되었다.
row와 col 모두 적용된 내림차순 관계에 대해 어떻게 코드로 반영할지가 이 문제의 관건이라고 생각한다.



### Javascript 모범답안
```javascript
var countNegatives = function (grid) {
    let rows = grid.length;
    let cols = grid[0].length;
    let lastNeg = grid[0].length - 1;           // 마지막 음수
    let res = 0;                                     // 음수 갯수

    for (let row = 0; row < rows; row++) {
        if (grid[row][0] < 0) {
            res += cols;        // 첫번째 원소부터 음수라면 col 갯수만큼 증가
            continue;           // 내림차순이므로, 어차피 모두 음수, 다음 row로 넘어감
        }
        if (grid[row][cols - 1] > 0) {
            continue;           // 마지막 col 원소가 양수일 경우, 음수가 없으므로 넘어감
        }

        // 원소로 음수를 일부 가진 row일 경우, 이진 탐색으로 음수를 찾아냄.
        let l = 0, r = lastNeg;
        while (l <= r) {
            let m = l + (r - l) / 2;    // 가운데 지점 확인
            m = Math.floor(m);      // 정수화
            if (grid[row][m] < 0) {
                r = m - 1;  // 음수라면, 더 앞쪽(수가 큰 쪽)에서 음수를 찾기 위해 끝 범위를 조정한다.
            } else {
                l = m + 1; // 양수라면, 다 뒷쪽(수가 작은 쪽)에서 음수를 찾기 위해 시작 범위를 조정한다.
            }
        }
        res += (cols-l); lastNeg = l;       // 이진 탐색이 종료 되었을 때, cols - 해당 index 갯수 만큼의 res를 추가한다.
    }
    return res;
};
```
모범 답안은 **56ms**의 속도가 나온 결과이다. fow문을 한 번 사용하여 rows간의 관계를 먼저 축약했다. (모두 음수이거나, 모두 양수일 때를 우선 판별하여 정리) 그 후에는 **이진 탐색**을 통해 row 내 col 간의 대소비교를 빠르게 실시했다. (음수를 빠르게 찾아냈다.) 이중 for문을 통해 모든 원소에 일일히 접근할 필요 없이, 불필요한 연산을 덜어내고 시작한 점과 이진 탐색을 이용한 점이 시간 절약의 요점이라 생각한다.


### Java
```java
NULL
```
풀이중




### Java 모범답안
```java
NULL
```
풀이중

------

풀이일자 : 2020.08.19   

사용 언어 : 
- Javascript 성공 (2020.08.19, 시간 : 72 ms, 메모리 : 37.3 MB)
- Java 미실시 (2020.08.  , 시간 : ms, 메모리 : MB)
