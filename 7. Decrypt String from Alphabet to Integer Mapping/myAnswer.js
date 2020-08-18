/**
 * @param {string} s
 * @return {string}
 */
var freqAlphabets = function(s) {
    var result = [];            // 결과값
    var target;                 // decrypt 해야할 타겟

    while(s.length) {
        // 문자열 길이만큼 반복

        target = s[s.length-1];     // 뒷부분 부터 탐색 시작.

        if (target === '#') {
            // 10번째 이상일 경우
            target = s.substr(s.length-3, 2); // # 앞의 2자리를 추출
            target = parseInt(target) - 10;   // 'j'보다 몇번째 뒤인지 계산

            result.unshift(String.fromCharCode('j'.charCodeAt() + target));
            // charCodeAt()을 통해 j의 유니코드 값 + n번째로 알파벳을 계산하고
            // 그 값을 통해 fromCharCode() 함수로 글자로 변환한다.
            // 마지막으로 unshift()를 통해 result의 맨 앞으로 붙여준다. (문자열 해독을 역순으로 하고 있기 때문에)

            s = s.substr(0, s.length-3);    // 계산이 완료된 '#' 포함 3자리는 제거
        }
        else {
            // '#'이 없다 = 9번째 이하일 경우
            target = parseInt(target) - 1;  // 'a'보다 몇번째 뒤인지 계산

            result.unshift(String.fromCharCode('a'.charCodeAt() + target));
            // charCodeAt()을 통해 a의 유니코드 값 + n번째로 알파벳을 계산하고
            // 그 값을 통해 fromCharCode() 함수로 글자로 변환한다.
            // 마지막으로 unshift()를 통해 result의 맨 앞으로 붙여준다. (문자열 해독을 역순으로 하고 있기 때문에)

            s = s.substr(0, s.length-1);    // 계산이 완료된 1자리는 제거
        }
    }

    return result.join('');
    // 배열을 단순히 toString()을 통해 문자열 변환시 원소 사이마다 ','가 붙기 때문에
    // join('');을 넣어주어 원소끼리 이어지도록 함.
    // join(구분자) 함수는 원소 사이에 구분자를 넣어 하나의 문자열로 만들어준다.
};

console.log("result = " + freqAlphabets("10#11#12"));   // 결과 확인
