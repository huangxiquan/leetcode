package bst;

import com.oracle.tools.packager.Log;
import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by huangxiquan on 2020/5/13.
 */
public class BST<E extends Comparable<E>> {

    class Node {
        public E e;
        public Node left;
        public Node right;
        public int depth;
        public int count;
        public Node(E e) {
            this.e = e;
        }
        public Node(E e,int depth) {
            this.e = e;
            this.depth = depth;
        }
    }

    private int size;
    private Node root;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return  size == 0;
    }

    public void add(E e) {
        root = add(root,e,0);
    }

    private Node add(Node node, @NotNull E e,int depth) {
        if(node == null) {
            return new Node(e,depth);
        }
        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left,e,++depth);
            node.count += 1;
        }else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right,e,++depth);
            node.count += 1;
        }
        size ++;
        return node;
    }
    //中序遍历
    public void middleOrder() {
        middleOrder(root);
    }

    private void middleOrder(Node node) {
        if(node == null) {
            return;
        }
        middleOrder(node.left);
        System.out.println(node.e);
        middleOrder(node.right);
    }

    public void preOrder() {
        if(isEmpty()) {
            System.out.println("empty");
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public E min() {
        return min(root).e;
    }

    private Node min(Node node) {
        if(node.left == null) {
            return node;
        }

        return min(node.left);
    }

    public Node removeMin() {
        Node min = min(root);
        root = removeMin(root);
        preOrder();
        return min;
    }

    private Node removeMin(Node node) {
        if(node.left == null) {
            if(node.right == null) {
                return null;
            }else {
                Node right = node.right;
                node.right = null;
                right.depth --;
                return right;
            }
        }
        node.left = removeMin(node.left);
        size --;
        return node;
    }

    public void remove(E e){
        remove(root,e);
    }

    private Node remove(Node node,E e) {
        if(node == null) {
            return null;
        }
        if(e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;
        }else {
            if(node.left == null) {
                Node right = node.right;
                node.right = null;
                return right;

            }else if(node.right == null) {
                Node left = node.left;
                node.left = null;
                return left;
            }else {
                Node min = min(node.right);
                System.out.println("min:" + min.e);
                Node newNode = removeMin(node.right);
                System.out.println("remove complete:" + newNode);
                min.left = node.left;
                min.right = newNode;
                node.left = null;
                min.depth --;
                return min;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{28,16,13,22,30,29,42,14};
        BST<Integer> bst = new BST<>();
        for(int num : nums) {
            bst.add(num);
        }
//        bst.middleOrder();
//        System.out.println("==========");
//        bst.preOrder();
//        System.out.println("==========");
//        System.out.println(bst);
//        bst.removeMin();
        System.out.println(bst);
        bst.remove(30);
        System.out.println(bst);
        bst.middleOrder();

    }

    @Override
    public String toString() {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        StringBuilder builder = new StringBuilder();
        int currentDepth = 0;
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            if(node.depth != currentDepth) {
                builder.append("\n");
                currentDepth = node.depth;
            }
            for(int i = 0 ; i < node.count + node.depth ; i++) {
                builder.append(" ");
            }
            builder.append(node.e);
            if(node.left != null) {
                nodes.add(node.left);
            }
            if(node.right != null) {
                nodes.add(node.right);
            }
        }

        return builder.toString();
    }
}
