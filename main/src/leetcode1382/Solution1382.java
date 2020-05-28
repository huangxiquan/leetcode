package leetcode1382;

import base.TreeNode;

/**
 * Created by huangxiquan on 2020/5/28.
 * 1382:将二叉搜索树变平衡 https://leetcode-cn.com/problems/balance-a-binary-search-tree/
 */
public class Solution1382 {
    public TreeNode balanceBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        //平衡左子树
        root.left = balanceBST(root.left);
        //平衡右子树
        root.right = balanceBST(root.right);
        //平衡当前节点
        //获取当前节点的平衡因子
        TreeNode resultNode = root;
        int factor = getBalanceFactor(root);
        if(factor < -1) {
            //维护平衡
            //RR
            System.out.println("factor:" + factor);
            if(getBalanceFactor(root.right) <= 0) {
                resultNode = leftRotate(root);
            }else {
                //RL
                root.right = rightRotate(root.right);
                resultNode = leftRotate(root);

            }
        }else if(factor > 1) {
            System.out.println("factor:" + factor);
            //维护平衡
            //LL
            if(getBalanceFactor(root.left) >= 0) {
                resultNode = rightRotate(root);
            }else {
                //LR
                root.left = leftRotate(root.left);
                resultNode = rightRotate(root);
            }
        }else {
            return resultNode;
        }
        System.out.println(isBalanceTree(root));
        return balanceBST(resultNode);
    }

    private TreeNode rightRotate(TreeNode node) {
        TreeNode root = node.left;
        TreeNode temp = root.right;
        root.right = node;
        node.left = temp;
        return root;
    }

    //左旋转
    private TreeNode leftRotate(TreeNode node) {
        TreeNode root = node.right;
        TreeNode temp = root.left;
        root.left = node;
        node.right = temp;
        return root;
    }

    private int getBalanceFactor(TreeNode root) {
        if(root == null) {
            return 0;
        }
       return getHeight(root.left) - getHeight(root.right);
    }

    private int getHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left),getHeight(node.right)) + 1;
    }

    private boolean isBalanceTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(Math.abs(getBalanceFactor(root)) > 1) {
            return false;
        }
        return isBalanceTree(root.left) && isBalanceTree(root.right);
    }

    public static void main(String[] args) {
//        [1,null,15,14,17,7,null,null,null,2,12,null,3,9,null,null,null,null,11]
        int[] nums = new int[]{1,15,14,17,7,2,12};
        TreeNode treeNode = new TreeNode(0);
        for(int i = 0 ; i < nums.length ; i++) {
            treeNode.add(nums[i]);
        }
        System.out.println(treeNode);
        treeNode.midOrder();
        Solution1382 solution1382 = new Solution1382();
        treeNode.root = solution1382.balanceBST(treeNode.root);
        System.out.println(treeNode);
        System.out.println(solution1382.isBalanceTree(treeNode.root));
    }


}
