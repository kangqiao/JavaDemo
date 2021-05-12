package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

public class CompleteBinaryTree完全二叉树 {

    /**
     * 求完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        while (l != null) {
            l = l.left;
            hl++;
        }

        while (r != null) {
            r = r.right;
            hr++;
        }

        //如果左右子树的高度相同, 说明是一棵满二叉树
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }

        //如果左右高度不同, 则按照变通二叉树的逻辑计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    public static void main(String[] args) {

    }
}
