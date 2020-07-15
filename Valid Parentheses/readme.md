## Valid Parentheses
괄호열이 주어진 Text를 입력받았을 때, 서로 열고 닫힘 관계가 분명한가?   
짝이 맞다면 true, 아니면 false   
[문제 출처 링크](https://leetcode.com/problems/valid-parentheses)

풀이일자 : 2020.07.13   
사용 언어 : 
 - javascript 성공 (시간 : 92 ms , 메모리 : 33.3 MB 소요)
 - java 성공 (시간 : 5 ms, 메모리 : 39.4MB 소요)   
 
 빠르게 푸는 인원의 경우 정규식을 사용하나, 정규식은 아직 능숙치 않아 일일히 찾는 방식을 사용했다.
 스택에 여는 괄호를 저장하고, 닫는 괄호와 비교하며 짝이 맞는지를 확인하도록 했다.
