package com.zp.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC145二叉树后序遍历 {

    //递归实现
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        post(root, list);
        return list;
    }

    private void post(TreeNode root, List<Integer> list) {
        if (root != null) {
            post(root.left, list);
            post(root.right, list);
            list.add(root.val);
        }
    }

    //栈实现
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        TreeNode prev = null;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                pNode = stack.pop();
                if (pNode.right == null || prev == pNode.right) {
                    list.add(pNode.val);
                    prev = pNode;
                    pNode = null;
                } else {
                    stack.push(pNode);
                    pNode = pNode.right;
                }
            }
        }
        return list;
    }
}
