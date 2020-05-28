package base;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by huangxiquan on 2020/5/28.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public TreeNode root;

    public void add(int value) {
        root = add(root,value);
    }

    private TreeNode add(TreeNode node, int value) {
        if(node == null) {
            return new TreeNode(value);
        }
        if(value < node.val) {
            node.left = add(node.left,value);
        }else if(value > node.val) {
            node.right = add(node.right,value);
        }
        return node;
    }

    public void midOrder() {
        midOrder(root);
    }

    private void midOrder(TreeNode node) {
        if(node == null) {
            return;
        }
        midOrder(node.left);
        System.out.println(node.val);
        midOrder(node.right);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(node != null) {
                builder.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }else {
                builder.append("null,");
            }

        }
        builder.append("]");
        return builder.toString();
    }
}
