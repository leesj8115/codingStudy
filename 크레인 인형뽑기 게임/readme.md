# Remove Element 
[문제 출처 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

> 게임개발자인 죠르디는 크레인 인형뽑기 기계를 모바일 게임으로 만들려고 합니다.
죠르디는 게임의 재미를 높이기 위해 화면 구성과 규칙을 다음과 같이 게임 로직에 반영하려고 합니다.

자세한 문제 설명은 문제 링크 참고
-----

## 나의 풀이

### Javascript
```javascript
function solution(board, moves) {
    var answer = 0;    // 없어진 인형의 갯수
    var index;
    // 1 ~ moves번째를 0 ~ moves.length-1의 인덱스로 변환해서 저장하는 변수
    var temp = [];      // 크레인을 통해 뽑은 값을 저장하는 배열

    for (var i = 0; i < moves.length; i++) {
        index = moves[i] - 1;   // index번째 위치를 확인

        for (var k = 0; k < board.length; k++) {
            if (board[k][index] !== 0) {
                temp.push(board[k][index]);     // index번째의 가장 위에 있는 인형 값을 temp에 저장
                board[k][index] = 0;               // 뽑은 인형 자리는 0의 값으로 입력
                break;
            }
        }
        
    }

    for (var i = 0; i< temp.length-1; i++) {
        if (temp[i] === temp[i+1]) {
                // temp안에 같은 종류 2개가 붙어있다면
                answer+=2;  // 인형 2개가 사라지므로 +2
                temp.splice(i, 2);  // 사라진 자리는 삭제

                i = -1;     // 다시 겹치는 부분이 있는지 탐색 시작
        }
    }
    return answer;
}

```

처음 Lv1 테스트로 실시했을 때, 시간안에 푸는 것을 실패했다...
같은 인형 2개를 발견했을 때 +2를 해야할 것을 +1로 하며 착오가 늘어갔다.

아직도 갈 길은 멀었고, 취직하긴 힘들 것 같다..

### Javascript 모범답안
```javascript
const transpose = matrix =>
    matrix.reduce(
        (result, row) => row.map((_, i) => [...(result[i] || []), row[i]]), []
    );

const solution = (board, moves) => {
    const stacks = transpose(board).map(row =>
        row.reverse().filter(el => el !== 0)
    );
    const basket = [];
    let result = 0;

    for (const move of moves) {
        const pop = stacks[move - 1].pop();
        if (!pop) continue;
        if (pop === basket[basket.length - 1]) {
            basket.pop();
            result += 2;
            continue;
        }
        basket.push(pop);
    }

    return result;
};
};
```
위의 코드를 해석하는 것도 어렵다..

------

풀이일자 : 2020.09.09    
사용 언어 : 
- Javascript 성공
