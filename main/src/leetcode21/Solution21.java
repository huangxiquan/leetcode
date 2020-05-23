package leetcode21;

import base.LinkedList;
import base.ListNode;

/**
 * Created by huangxiquan on 2020/5/13.
 * 21:合并两个有序链表 https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class Solution21 {



    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l2 == null) {
            return l1;
        }
        if(l1 == null) {
            return l2;
        }
        ListNode dummyNode1 = new ListNode(-1);
        dummyNode1.next = l1;
        ListNode pre = dummyNode1;
        while (pre.next != null) {
            if(l2.val > pre.next.val) {
                pre = pre.next;
            }else {
                break;
            }
        }
        ListNode temp = l2.next;
        l2.next = pre.next;
        pre.next = l2;
        return mergeTwoLists(dummyNode1.next,temp);
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l2 == null) {
            return l1;
        }
        if(l1 == null) {
            return l2;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists1(l1,l2.next);
            return l2;
        }

    }


    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList(new int[]{1,3});
        LinkedList linkedList2 = new LinkedList(new int[]{2});
        System.out.println("linkedList1:" + linkedList1.root);
        System.out.println("linkedList2:" + linkedList2.root);

        ListNode result = new Solution21().mergeTwoLists1(linkedList1.root,linkedList2.root);
        System.out.println("result:" + new LinkedList(result));
    }
}
