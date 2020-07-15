/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode finalNode(ListNode l) {
        ListNode temp = l;

        while (temp.next != null) {
            temp = temp.next;
        }

        return temp;
    }
    
    public int listLength(ListNode l) {
        int i = 1;
        ListNode temp = l;
        
        if (temp == null) {
            return 0;
        }

        while(temp.next != null) {
            temp = temp.next;
            i++;
        }

        return i;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode(0);  // 하나의 리스트를 이어주기 위한 시작점 answer 생성
        ListNode nextNode = new ListNode();
        int length = listLength(l1) + listLength(l2);

        if (length == 0) {
            return l1;
        }
        else if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }

        while(true) {
            if (l1.val > l2.val) {
                // l2의 노드 값이 작거나 같다면
                nextNode = l2.next; // l2의 노드를 가져오기 위해 다음 경로는 미리 저장 (백업)
                l2.next = null;     // l2의 노드 분리
                finalNode(answer).next = l2;    // 분리된 노드를 answer에 연결
                if (nextNode == null) {
                    // 다음 이어질 노드가 없다면 남은 l1의 노드를 모두 연결
                    while(true) {
                        nextNode = l1.next; // l1의 노드를 가져오기 위해 다음 경로는 미리 저장 (백업)
                        l1.next = null;     // l1의 노드 분리
                        finalNode(answer).next = l1;    // 분리된 노드를 answer에 연결
                        if (nextNode == null) {
                            break;  // 다음 이어질 노드가 없다면 정지
                        } else {
                            l1 = nextNode; // 아니라면 l2로 다음 노드를 가리켜 리스트 탐색 계속 진행
                        }
                    }
                    
                    break;  // 종료
                }
                else {
                    l2 = nextNode; // 아니라면 l2로 다음 노드를 가리켜 리스트 탐색 계속 진행
                }
            }
            else {
                // l1의 노드 값이 작거나 같다면
                nextNode = l1.next; // l1의 노드를 가져오기 위해 다음 경로는 미리 저장 (백업)
                l1.next = null;     // l1의 노드 분리
                finalNode(answer).next = l1;    // 분리된 노드를 answer에 연결
                
                if (nextNode == null) {
                    // 다음 이어질 노드가 없다면 남은 l2의 노드를 모두 연결
                    while(true) {
                        // l2의 노드 값이 작거나 같다면
                        nextNode = l2.next; // l2의 노드를 가져오기 위해 다음 경로는 미리 저장 (백업)
                        l2.next = null;     // l2의 노드 분리
                        finalNode(answer).next = l2;    // 분리된 노드를 answer에 연결

                        if (nextNode == null) {
                            // 다음 이어질 노드가 없다면
                            break;  // 정지
                        } else {
                            l2 = nextNode; // 아니라면 l2로 다음 노드를 가리켜 리스트 탐색 계속 진행
                        }
                    }

                    break;  // 종료
                }
                else {
                    l1 = nextNode; // 아니라면 l1로 다음 노드를 가리켜 리스트 탐색 계속 진행
                }
            }
        }

        answer = answer.next;   // 시작점 노드 answer 첫 부분 제거
        return answer;
    }
}
