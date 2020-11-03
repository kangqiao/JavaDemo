package com.zp.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC94二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                TreeNode node =  stack.pop();
                list.add(node.val);
                pNode = pNode.right;
            }
        }
        return list;
    }


}
