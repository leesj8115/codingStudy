# 두 개 뽑아서 더하기
[문제 출처 링크](https://programmers.co.kr/learn/courses/30/lessons/68644/)

> 정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.    

두 원소의 합을 원소로 갖는 answer를 반환하는 문제이다. 문제 자체는 단순하나, 어떻게 또 다른 사람들이 풀었을지 궁금하다.
-----

## 나의 풀이

### Javascript
```javascript
function solution(numbers) {
    var answer = [];
    var temp;

    // 단순한 sort()시 ASCII값 기준 정렬을 실시하므로
    // 오름차순 정렬 실시 조건으로 변경
    numbers.sort(function (a, b) {
        return a - b;
    });

    for (var i = 0; i < numbers.length; i++) {
        for (var k = i + 1; k < numbers.length; k++) {
            // 이중 for문 사용, 원소 전체 탐색
            temp = numbers[i] + numbers[k]; // 두 원소의 합 임시 저장

            if (answer.indexOf(temp) === -1) {
                answer.push(temp); // 중복 원소가 아니라면 추가
            }
        }
    }
    
    // number.sort()를 안쓰는 이유와 동일
    answer.sort(function (a, b) {
        return a - b;
    });
    
    return answer;
}
```
제일 단순한 방법을 사용했다. 두 원소를 더하되, 이미 있는 원소가 아닐 시에만 answer에 추가하도록 했다.



### Javascript 모범답안
```javascript
function solution(numbers) {
    const temp = []

    for (let i = 0; i < numbers.length; i++) {
        for (let j = i + 1; j < numbers.length; j++) {
            temp.push(numbers[i] + numbers[j])
        }
    }

    const answer = [...new Set(temp)]

    return answer.sort((a, b) => a - b)
}
```
모범 답안 또한 원리는 동일하다. 다만 Set을 사용하여 answer를 정의하였는데, Set의 활용방법에 대해 더 고민할 필요가 있겠다. 또한 sort()의 사용 방법도 내가 사용한 방법보다 훨씬 깔끔한 코드로 완성이 되었다.

------

풀이일자 : 2020.10.06
사용 언어 : 
- Javascript
