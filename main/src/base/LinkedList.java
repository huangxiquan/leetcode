package base;

/**
 * Created by huangxiquan on 2020/5/13.
 */
public class LinkedList {

    public ListNode root;

    public LinkedList(int[] arr) {
        for(int x : arr) {
            add(x);
        }
    }

    public LinkedList(ListNode node) {
        root = node;
    }

    public void add(int x) {
        root = add(root,x);
    }

    private ListNode add(ListNode node, int x) {
        if(node == null) {
            return new ListNode(x);
        }
        node.next = add(node.next,x);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode current = root;
        stringBuilder.append("[");
        while (current != null) {
            stringBuilder.append(current.val + ",");
            current = current.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
