package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

/**
 * 二叉搜索树
 * 在一个二叉树中, 任意节点的值要大于等于左子树的所有节点的值, 且要小于等于右子树的所有节点的值.
 * 二叉树算法的设计总路线: 明确一个节点要做的事情, 然后剩下的事抛给递归框架.
 */
public class BST {

    /**
     * 把二叉树的所有节点中的值 +1
     * @param root
     */
    public static void plusOne(TreeNode root) {
        if (root == null) return;
        root.val += 1;
        plusOne(root.left);
        plusOne(root.right);
    }

    /**
     * 如何判断两棵二叉树是否完全相同?
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    /**
     * 判断BST是否合法
     * 在一个二叉树中, 任意节点的值要大于等于左子树的所有节点的值, 且要小于等于右子树的所有节点的值.
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    //这样相当于给子树上的所有节点添加了一个min和max的边界, 约束root的左子树节点值不超过root的值, 右子树的节点值不小于root的值.
    private static boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val > max.val) return false;
        return isValidBST(root.left, min, root)
                && isValidBST(root, root, max);
    }

    /**
     * 在BST查找一个数是否存在
     * @param root
     * @param target
     * @return
     */
    public static boolean isInBST(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) {
            return true;
        } else if (root.val > target) {
            return isInBST(root.left, target);
        } else {
            return isInBST(root.right, target);
        }
    }

    /**
     * 在BST中插入一个数
     * @param root
     * @param val
     * @return
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
       if (root == null) return new TreeNode(val);
       //如果已存在, 不需要创建, 直接返回.
       if (root.val == val) return root;
       if (root.val > val) {
           root.left = insertIntoBST(root.left, val);
       }
       if (root.val < val) {
           root.right = insertIntoBST(root.right, val);
       }
       return root;
    }
}
