package leetcode203;

import base.ListNode;

/**
 * Created by huangxiquan on 2020/5/12.
 * 203:移除链表元素 https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return null;
        }
        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,6,3,4,5,6};
        ListNode dummyNode = new ListNode(-1);
        ListNode currentNode = dummyNode;
        for(int i = 0; i < nums.length ; i++) {
            currentNode.next = new ListNode(nums[i]);
            currentNode = currentNode.next;
        }

        ListNode cur = new Solution203().removeElements(dummyNode.next,6);
        StringBuilder stringBuilder = new StringBuilder();
        while (cur != null) {
            stringBuilder.append("-->" + cur.val);
            cur = cur.next;
        }
        System.out.println(stringBuilder);
    }
}
