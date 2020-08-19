public class Solution {

    public static String[] box = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };  // "a"부터 "z"를 담고있는 String 배열


    public static String freqAlphabets(String s) {
        String result = "";         // 결과값
        int key;                    // box의 알파벳에 접근할 index
        int len = s.length();       // 처리 중인 문자열의 길이


        while(!s.isEmpty()) {
            // s가 비어있을 때 까지 반복

            if(s.charAt(len-1) == '#') {
                // #을 발견했을 경우

                key = ((s.charAt(len-3) - '0') * 10) + (s.charAt(len-2) - '0') - 1;
                // 2자리에 대한 key 값을 계산한다.
                // charAt(index) - '0'을 통해 char를 int 값으로 변환한다.
                result = box[key].concat(result);
                // result에 붙여준다. 이때 뒷부분부터 계산하므로 concat 함수를 통해 결과값이 가장 앞에 오도록 한다.

                len-=3;                     // # 포함 3자리를 사용하였으므로 -3
                s = s.substring(0, len);    // 계산이 완료된 부분은 제거
            }
            else {
                // #이 없는 1자리일 경우
                key = s.charAt(len-1) - '0' - 1;        // 1자리에 대한 char의 int화
                result = box[key].concat(result);       // key를 통해 글자 확인, 붙이기

                len-=1;                     // 1자리를 사용하였으므로 -1
                s = s.substring(0, len);    // 계산이 완료된 부분은 제거
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "10#11#12";

        System.out.println("result = " + freqAlphabets(s));

        return ;
    }
}
