package com.zp.demo.algorithm;

public class TreeNode {
    int key;
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public TreeNode(int key, int val, TreeNode next, TreeNode prev) {
        this.key = key;
        this.val = val;
        this.left = next;
        this.right = prev;
    }
}
