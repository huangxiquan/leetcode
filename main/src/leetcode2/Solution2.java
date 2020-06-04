package leetcode2;

import base.ListNode;

/**
 * Created by huangxiquan on 2020/5/31.
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        int x = 0;
        while (l1 != null || l2 != null || x != 0) {
            if(l1 == null && l2 == null) {
                current.next = new ListNode(x);
                x = 0;
                current = current.next;
                continue;
            }else if(l1 == null) {
                int temp = l2.val + x;
                x = temp / 10;
                current.next = new ListNode(temp % 10);
                l2 = l2.next;
                current = current.next;
                continue;
            }else if(l2 == null) {
                int temp = l1.val + x;
                x = temp / 10;
                current.next = new ListNode(temp % 10);
                current = current.next;
                l1 = l1.next;
                continue;
            }
            int temp = l1.val + l2.val + x;
            x = temp / 10;
            current.next = new ListNode(temp % 10);
            l1 = l1.next;
            l2 = l2.next;
            current = current.next;
        }

        return result.next;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,8};
        int[] nums2 = new int[]{0};
        ListNode listNode1 = new ListNode(0);
        listNode1.add(nums1);
        ListNode listNode2 = new ListNode(0);
        listNode2.add(nums2);
        System.out.println(new Solution2().addTwoNumbers(listNode1.next,listNode2.next));

    }
}
