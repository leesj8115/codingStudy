class Solution {
    public boolean isValid(String s) {
        Stack<Integer> openStack = new Stack<>();   // 열린 괄호를 저장하는 스택
        String textBracket = "({[)}]";              // 괄호 종류를 저장한 텍스트
        int saveIndex;                              // 가장 최근에 열린 괄호의 index
        int i, temp;

        if ((s.length() % 2) == 1) {
            // 문자열이 홀수라면 짝이 확정적으로 맞을 수 없으므로 바로 false
            return false;
        }

        for (i = 0; i < s.length(); i++) {
            temp = textBracket.indexOf(s.substring(i, i+1));  // 현재 입력된 괄호의 종류 확인

            if (temp < 3) {
                // 여는 괄호였을 경우
                openStack.push(temp); // stack에 저장
            }
            else if (openStack.size() == 0) {
                // 닫는 괄호인데 열린 괄호가 앞에 없을 때 (= stack이 비어있을 때)
                // 짝이 안맞다는 의미이므로
                return false;
            }
            else {
                // 닫는 괄호인데 열린 괄호가 앞에 있을 때
                if ((temp - openStack.pop()) != 3) {
                    // 앞서 온 괄호가 같은 종류인지 비교. 다르다면 틀린 것
                    return false;
                }    
            }
        }

        if (openStack.size() != 0) {
            return false; // 마지막으로 짝을 만나지못하고 남은 열린 괄호가 있는지 확인
        }
        
        return true; // 모든 조건 충족시 
    }
}
