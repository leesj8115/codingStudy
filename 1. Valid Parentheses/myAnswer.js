var isValid = function(s) {
    var openStack = [];      // 열린 괄호의 저장
    let textBracket = "({[)}]";   // 괄호
    var saveIndex;          // 가장 최근에 열린 괄호의 index
    var i;

    // 홀수씨는 짝이 맞을리 없으니
    if ((s.length%2) === 1)
        return false;

    for (i = 0; i < s.length; i++) {
        temp = textBracket.indexOf(s[i]);
        
        if (temp < 3) {
            // 여는 괄호였을 경우
            openStack.push(temp); // 열린 괄호의 index를 push
        }
        else {
            // 닫는 괄호였을 경우
            if ((temp - openStack.pop()) !== 3) {
                // 가장 최근에 열린 괄호와 닫는 괄호의 차이가 3이 아닌경우 false 반환
                // (3이 아니다 = 같은 종류가 아니다)
                // (index의 값이 서로 같은 종류일 경우 3의 차이가 나기 때문에..)
                return false;
            }
        }
    }

    if (openStack.length !== 0) {
        // 여는 괄호가 모두 닫히지 않았을 경우
        return false; // 닫힘 괄호와 쌍을 이루지 못했다는 의미이므로 false 반환
    }

    return true; // 다 뚫고 여기까지 왔다면 좋아요~
};
