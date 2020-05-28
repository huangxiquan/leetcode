package bst;

import com.oracle.tools.packager.Log;
import com.sun.istack.internal.NotNull;

import java.util.*;

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
        public int height;
        public Node(E e) {
            this.e = e;
        }
        public Node(E e,int depth) {
            this.e = e;
            this.depth = depth;
            this.height = 1;
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

        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;

        //维护平衡
        //RR
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1 && getBalanceFactor(node.right) < 0) {
            node = leftRotate(node);
        }
        //LL
        if(Math.abs(balanceFactor) > 1 && getBalanceFactor(node.left) > 0) {
            node = rightRotate(node);
        }
        //RL
        if(Math.abs(balanceFactor) > 1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }
        //LR
        if(Math.abs(balanceFactor) > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }
        return node;
    }
    //右旋转
    private Node rightRotate(Node node) {
        Node root = node.left;
        Node temp = root.right;
        root.right = node;
        node.left = temp;
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1;
        return root;
    }
    //左旋转
    private Node leftRotate(Node node) {
        Node root = node.right;
        Node temp = root.left;
        root.left = node;
        node.right = temp;
        //维护高度
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1;
        return root;
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
        int[] nums = new int[]{3,1,2};
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
        bst.isBst();
        System.out.println();
        System.out.println("isBalance:" + bst.isBalance());
//        bst.remove(30);
//        System.out.println(bst);
//        bst.middleOrder();

    }

    public void isBst() {
        ArrayList<E> arrayList = new ArrayList<>();
        isBst(root,arrayList);
        System.out.print("isBst:" + arrayList);
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private int getHeight(Node node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private boolean isBalance(Node node) {
        if(node == null) {
            return true;
        }
        if(Math.abs(getBalanceFactor(node)) >= 2) {
            //不平衡
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
    }

    private void isBst(Node node, ArrayList<E> arrayList) {
        if(node == null) {
            return;
        }
        isBst(node.left,arrayList);
        arrayList.add(node.e);
        isBst(node.right,arrayList);
    }

    @Override
    public String toString() {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        StringBuilder builder = new StringBuilder();
        int currentHeight = root.height;
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            if(node.height != currentHeight) {
                builder.append("\n");
                currentHeight = node.height;
            }
            for(int i = 0 ; i < node.count + node.height ; i++) {
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
