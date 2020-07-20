#include <iostream>
#include <vector>

struct ListNode {
        int val;
        ListNode *next;
        ListNode() : val(0), next(nullptr) {;}
        ListNode(int x) : val(x), next(nullptr) {;}
        ListNode(int x, ListNode *next) : val(x), next(next) {;}
};

class Solution {
public:
    ListNode* finalNode(ListNode *l) {
        ListNode *temp = l;
        while(temp->next != nullptr) {
            temp = l->next;
        }

        return temp;
    }

    int listLength(ListNode* l) {
        int i = 1;
        ListNode *temp = l;
        while (temp->next != nullptr) {
            temp = temp->next;
            i++;
        }

        return i;
    }

    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode *answer;
        int length = listLength(l1) + listLength(l2);
        std::cout << length << std::endl;
        ListNode *sortNode = new ListNode[length];
        int i = 0;

        while(true) {
            if (l1->val > l2->val) {
                sortNode[i].val = l2->val;
                sortNode[i].next = l2;
                if (l2->next == nullptr) {
                    break;
                }
                else {
                    l2 = l2->next;
                }
            }
            else {
                sortNode[i].val = l1->val;
                sortNode[i].next = l1;
                if (l1->next == nullptr) {
                    break;
                }
                else {
                    l1 = l1->next;
                }
            }

            i++;
        }

        i++;
        if (l1 == nullptr) {
            while(l2 != nullptr) {
                sortNode[i].val = l2->val;
                sortNode[i].next = l2;
                l2 = l2->next;
                i++;
            }
        }

        else {
            while(l1 != nullptr) {
                sortNode[i].val = l1->val;
                sortNode[i].next = l1;
                l1 = l1->next;
                i++;
            }
        }

        for (i = 0; i < length-1; i++) {
            sortNode[i].next = &sortNode[i+1];
        }
        sortNode[length-1].next = nullptr;

        answer = sortNode;

        return answer;
    }
};
