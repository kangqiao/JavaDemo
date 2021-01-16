package com.zp.demo.algorithm.tree;

//给定一个非空二叉树，返回其最大路径和。
//
// 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
//
//
//
// 示例 1：
//
// 输入：[1,2,3]
//
//       1
//      / \
//     2   3
//
//输出：6
//
//
// 示例 2：
//
// 输入：[-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//输出：42
// Related Topics 树 深度优先搜索
// 👍 812 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.zp.demo.Zlog;
import com.zp.demo.algorithm.model.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class LCH124二叉树中的最大路径和 {

    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        max(root);
        return ans;
    }

    private int max(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, max(root.left));
        int right = Math.max(0, max(root.right));

        ans = Math.max(ans, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        LCH124二叉树中的最大路径和 obj = new LCH124二叉树中的最大路径和();
        test2(obj);
    }

    private static void test2(LCH124二叉树中的最大路径和 obj) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        TreeNode tn20 = new TreeNode(20);
        root.right = tn20;
        tn20.left = new TreeNode(15);
        tn20.right = new TreeNode(7);
        Zlog.log(BinaryTree.preorder(root).toString());
        Zlog.log(obj.maxPathSum(root) + "");
    }

    private static void test1(LCH124二叉树中的最大路径和 obj) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        Zlog.log(obj.maxPathSum(root) + "");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
