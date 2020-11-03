package com.zp.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 剑指Offer32从上到下打印二叉树 {

    public int[] levelOrder(TreeNode root) {
        int[] ret = new int[0];
        List<Integer> list = new ArrayList<>();
        if (null == root) return new int[0];
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}
