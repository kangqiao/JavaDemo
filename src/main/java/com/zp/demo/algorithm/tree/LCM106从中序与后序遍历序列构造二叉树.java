package com.zp.demo.algorithm.tree;

//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
// 后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 深度优先搜索 数组
// 👍 416 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.zp.demo.Zlog;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LCM106从中序与后序遍历序列构造二叉树 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /**
         * 解题思路
         * 1. 后序遍历: 左子树->右子树->根结点
         * 2. 中序遍历: 左子树->根结点->右子树
         * 步骤1: 根据后序遍历的最后一位确认根结点开始位置.
         * 步骤2: 在中序遍历列表中查找根结点位置, 即左半部分是左子树, 右半部分是右子树.
         * 步骤3: 递归.
         */
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postorder.length - 1, inMap, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, int postStart, int postEnd, Map<Integer, Integer> inMap, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        //后序遍历
        TreeNode root = new TreeNode(postorder[postEnd]);
        //确认根结点在中序遍历中的位置.
        int inRoot = inMap.get(root.val);
        //计算出左子树部分的宽度
        int numsLeft = inRoot - inStart;

        //计算后序遍历的左部分: [postStart, postStart + numsLeft -1] => postStart + numsLeft -1 即为后序遍历左部分结束位
        root.left = buildTree(postorder, postStart, postStart + numsLeft - 1, inMap, inStart, inRoot - 1);
        //计算后序遍历的左部分: [postStart + numsLeft, postEnd] => postStart + numsLeft 即为后序遍历右部分起始位
        root.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inMap, inRoot + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        LCM106从中序与后序遍历序列构造二叉树 instance = new LCM106从中序与后序遍历序列构造二叉树();
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = instance.buildTree(inorder, postorder);
        Zlog.log(BinaryTree.inorder(root).toString());
        Zlog.log(BinaryTree.postorder(root).toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
