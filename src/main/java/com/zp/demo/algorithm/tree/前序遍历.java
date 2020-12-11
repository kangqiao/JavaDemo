package com.zp.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 前序遍历 {

    //递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    //栈实现
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                list.add(pNode.val);
                pNode = pNode.left;
            } else {
                TreeNode node = stack.pop();
                pNode = pNode.right;
            }
        }
        return list;
    }
}
