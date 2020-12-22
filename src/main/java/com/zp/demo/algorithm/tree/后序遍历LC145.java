package com.zp.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 后序遍历LC145 {

    //递归实现
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
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
                //比较右节点是否为空, 或者上次已经遍历过右节点了, 需要遍历中节点了.
                if (pNode.right == null || pNode.right == prev) {
                    list.add(pNode.val);
                    prev = pNode; //记录其上次右节点的位置
                    pNode = null; //设置为空, 下次从栈中弹出pNode.
                } else {
                    //如果右子树不为空, 将其继续入栈, 开始遍历右子树各节点.
                    stack.push(pNode);
                    pNode = pNode.right;
                }
            }
        }
        return list;
    }
}
