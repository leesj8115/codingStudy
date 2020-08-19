# Decrypt String from Alphabet to Integer Mapping
[문제 출처 링크](https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/)

> Given a string s formed by digits (`'0'` - `'9'`) and `'#'` . We want to map s to English lowercase characters as follows:   
>  - Characters (`'a'` to `'i'`) are represented by (`'1'` to `'9'`) respectively.
>  - Characters (`'j'` to `'z'`) are represented by (`'10#'` to `'26#'`) respectively. 
>
> Return the string formed after mapping.
> 
> It's guaranteed that a unique mapping will always exist.

입력받은 문자열 s에 대해 #이 붙었을 경우엔 10번째 이상의 알파벳을, 그 외엔 1-9번째 알파벳으로 변환하여 출력하는 문제이다. 이때 **시간 복잡도**를 고려해야 한다.

-----

## 나의 풀이

### Javascript
```javascript
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
```

결과적으로 소요 시간은 88 ~ 92ms로, 빠른 편에 속하지 않았다. 1 부터 26과 1:1 대응하는 배열을 만들어 원소를 비교하면 훨씬 빠른 속도로 작업이 가능할 것 같다. 하지만 이러한 계산을 하는 것이 더 복잡하다고 판단되어 제공된느 함수들을 통해 작업했다. 특히 substr을 통해 문자열을 추가 생산하는 점과 fromCharCode와 charCodeAt을 통해 글자 값을 직접 계산한 부분이 소요 시간을 증가시킨 원인이라고 생각한다. 복잡도 자체는 O(n) 으로 판단하나, 훨씬 오래 걸린다...



### Javascript 모범답안
```javascript
var freqAlphabets = function(s) {
    const alpha = {
        '1':'a', '2':'b', '3':'c', '4':'d', '5':'e',
        '6':'f', '7':'g', '8':'h', '9':'i', '10':'j',
        '11':'k', '12':'l', '13':'m', '14':'n', '15':'o',
        '16':'p', '17':'q', '18':'r', '19':'s', '20':'t',
        '21':'u', '22':'v', '23':'w', '24':'x', '25':'y',
        '26':'z';
    };
   
    var arr = s.split(''), res = [];
    
    while(arr.length > 0){
        var x = '', n = arr.length-1;
        
        if (arr[n] === '#'){
            x+=arr[n-2];
            x+=arr[n-1];
            arr.splice(-3);
        }
        else {
            x+=arr[n];
            arr.pop();
        }
        res.unshift(alpha[x]);
    }
    
    return res.join('');
    
};
```
모범 답안의 경우, const에 먼저 알파벳 갯수인 26과 각 알파벳의 대응 값을 담아둔 배열을 만들어두어 원소 값을 일일히 계산하는 나보다 시간적 이득을 얻었고, s를 배열 형태로 만들어 원소에 직접 접근했기 때문에 시간을 아꼈다. x의 형태도 `var x = '';`로 선언되었기 때문에 후에 이어진 `x += arr[n-2]`같은 형태의 코드에서 1+2가 아닌 12로 올바르게 접근할 수 있었다. 위의 모범답안의 소요 시간은 48ms로, 1/2의 시간 소요를 나타냈다. 시간이 중요할 때는 불필요한 계산은 최대한 버리는 것이 좋겠다..


### Java
```java
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
```
javascript의 모범답안을 참고하여 알파벳을 담고있는 배열을 선언하고, 이후 index값만 계산하여 해당 알파벳에 접근, 사용하였다. 속도가 3ms가 나와서 잘했나 싶었지만, 언어의 특성 때문에 javascript보다 빨리 나온 것이었고, 가장 많은 분포를 가진 구간은 1ms 구간이었다. 




### Java 모범답안
```java
class Solution {
    public String freqAlphabets(String s) {
       int num=0;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
           // System.out.println(i+"      ");
            if(i<s.length()-2 && s.charAt(i+2)=='#')
            {
               // System.out.println("inside      "+s.charAt(i)+"         "+s.charAt(i+1));
                num=(s.charAt(i)-'0')*10+(s.charAt(i+1)-'0');
                i=i+2;
            }
            else{
                num=s.charAt(i)-'0';
            }
           // System.out.println("num=.  "+num);
            char c=(char)(num-1+'a');
           // System.out.println("c=.  "+c);
            sb.append(c);
        }
        return sb.toString();
    }
}
```
모범 답안의 경우, 0ms 대의 기록이다. 알파벳을 직접 배열에 담을 필요 없이, 'a'의 값에 index만 계산하여 더하면 훨씬 간결한 결과를 얻을 수 있다. 또한 뒤에서부터 계산하여 복잡하게 문자열을 만들어간 나와 달리, 앞에서 부터 계산하여 `charAt(i+2) == '#'`이라는 단순한 방법을 사용하여 앞에서부터 문자열을 확인하였고 `append`를 통해 연결하였다. index를 이용한다는 점은 javascript의 모범답안에서 확인했지만, 애초에 배열에 담을 필요가 없었다는 점에서 반성하게 된다.

------

풀이일자 : 2020.08.19-20
사용 언어 : 
- Javascript 성공 (2020.08.19, 시간 : 90 ms, 메모리 : 37.6 MB)
- Java 성공 (2020.08.20, 시간 : 3ms, 메모리 : 41.1 MB)
