package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的操作
 */
public class BinaryTree {

    /**
     * 求二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private static void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private static void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
    }

    /**
     * 从数组的某个位置的元素开始生成树
     * 约定：二叉树采用宽度优先遍历来数组化，二叉树的节点按照BFS的顺序依次存储在数组内，数组中的’#’代表空节点，末尾的’#’可省略。
     *     1
     *    / \
     *   2   3
     *  / \   \
     * 4   5   6
     *    / \
     *   7   8
     * 这棵树会被序列化为：{1,2,3,4,5,#,6,#,#,7,8}，后面的四个#被省略。
     */
    public static TreeNode createTree(int[] list, int start) {
        if (list == null || list.length == 0 || list[start] == '#') {
            return null;
        }

        TreeNode root = new TreeNode(list[start]);
        int left = 2 * start + 1;
        int right = 2 * start + 2;
        if (left > list.length - 1) {
            root.left = null;
        } else {
            root.left = createTree(list, left);
        }

        if (right > list.length - 1) {
            root.right = null;
        } else {
            root.right = createTree(list, right);
        }

        return root;
    }


}
