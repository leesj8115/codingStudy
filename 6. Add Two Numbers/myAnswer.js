/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val);
    this.next = (next === undefined ? null : next);
};

function printListNode(temp) {
    let a = "";

    while(true) {
        a += temp.val;

        if (temp.next === null) {
            break;
        }
        else {
            a += " -> ";
            temp = temp.next;
        }

    }

    console.log(a);
};

var addTwoNumbers = function(l1, l2) {
    var result = new ListNode();    // 결과값이 들어갈 노드
    var temp, over; // temp = l1과 l2의 val 합, over = 합이 10을 넘었을 경우

    // 예외처리
    if ((l1 === null) && (l2 === null)) {
        return null;    // 둘다 비었을 시 null 반환
    }
    else if (l1 === null) {
        result.val = l2.val;       // l1이 비었다면 l2의 값만 계산
        result.next = addTwoNumbers(l2.next ,null); // l2의 다음 노드에 대해 계산
    }
    else if (l2 === null) {
        result.val = l1.val;       // l2이 비었다면 l1의 값만 계산
        result.next = addTwoNumbers(l1.next ,null); // l1의 다음 노드에 대해 계산
    }
    else {
        // 예외처리 이후, l1과 l2 두 노드가 살아있을 때에 대해 계산
        temp = l1.val + l2.val;
        over = (temp < 10 ? 0 : 1);
    
        if (over === 0) {
            // 합이 10을 넘지 않았을 경우
            result.val = temp;
            result.next = addTwoNumbers(l1.next, l2.next);  // 다음 원소에 대해 진행
        }
    
        else {
            // 합이 10을 넘은 경우
            result.val = temp % 10;
            result.next = addTwoNumbers(addTwoNumbers(new ListNode(1, null), l1.next), l2.next);    // over값 1에 대한 중복 계산 수행
        }
    }

    return result;  // 재귀를 통해 반환된 result 반환
};

//var a = new ListNode(5, null);
//var b = new ListNode(5, null);
var a = new ListNode(1, new ListNode(8, null));
var b = new ListNode(0, null);
//var a = new ListNode(2, new ListNode(4, new ListNode(3, null)));
//var b = new ListNode(5, new ListNode(6, new ListNode(4, null)));
//printListNode(a);
var r = addTwoNumbers(a, b);
printListNode(r);
