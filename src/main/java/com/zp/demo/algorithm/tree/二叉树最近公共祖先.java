package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class 二叉树最近公共祖先 {

    /**
     * 后序遍历二叉树, 从下往上找到公共祖先节点.
     * 情况1, 如果p和q都在以root为根的树中, 函数返回p和q的最近公共祖先节点.
     * 情况2, 如果p和q都不在以root为根的树中, 函数返回null.
     * 情况3, 如果p和q只有一个在以root为根的树中, 函数返回那个节点.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //case1: 如果p和q都在以root为根的树中, 那么left和right一定分别是p和q
        if (left != null && right != null) return root;
        //case2: 如果p和q都不在以root为根的树中, 直接返回null;
        if (left == null && right == null) return null;
        //case3: 如果p和q只有一个存在于以root为根的树中, 函数返回该节点.
        return left != null ? left: right;
    }
}
