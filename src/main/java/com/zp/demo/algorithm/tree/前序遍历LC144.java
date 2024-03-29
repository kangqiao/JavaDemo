package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 前序遍历LC144 {

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
                list.add(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
        return list;
    }

    //栈实现
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }
}
