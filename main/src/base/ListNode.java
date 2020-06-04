package base;

/**
 * Created by huangxiquan on 2020/5/12.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }


    public void add(int[] arr) {
        ListNode current = this;
        for(int i = 0 ; i < arr.length ; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        ListNode current = this;
        while (current != null) {
            stringBuilder.append(current.val + ",");
            current = current.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
