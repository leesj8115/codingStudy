## Merge Two Sorted Lists
2개의 리스트를 하나의 리스트로 생성, 정렬   
[문제코드 링크](https://leetcode.com/problems/merge-two-sorted-lists)

풀이일자 : 2020.07.15   
사용 언어 : 
 - java 성공 (시간 : 2 ms, 메모리 : 40.5 MB 소요)
 - C++ 실패 (사유 : alloc-dealloc-mismatch)   
 
 노드를 계속 비교하고 작은 값의 노드를 분리, answer에 연결하는 작업을 반복했다.
 l1 대상인지, l2 대상인지만 다를 뿐 작업하는 내용은 똑같은데, 함수로 분리하지 못하여 매우 지저분한 코드가 탄생했다.
 시간 여유가 생기면 함수로 분리하여 실행단은 깔끔하게 보이도록 해야겠다.
