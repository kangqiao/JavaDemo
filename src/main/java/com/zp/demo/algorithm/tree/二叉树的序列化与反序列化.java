package com.zp.demo.algorithm.tree;

import com.zp.demo.algorithm.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的序列化与反序列化 {
    public static final String NULL = "#";
    public static final String SEP = ",";

    /**
     * 前序 - 把一棵二叉树序列化成字符串
     * @param root
     * @return
     */
    public String preorderSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorderSerialize(root, sb);
        return sb.toString();
    }

    private void preorderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        preorderSerialize(root.left, sb);
        preorderSerialize(root.right, sb);
    }

    /**
     * 前序 - 把字符串反序列化成二叉树
     * @param data
     * @return
     */
    public TreeNode preorderDeserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        for (String n: data.split(SEP)) {
            list.addLast(n);
        }
        return preorderDeserialize(list);
    }

    private TreeNode preorderDeserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) return null;
        //*****前序遍历位置*****//
        //列表最左侧九是根节点
        String firist = nodes.removeFirst();
        if (NULL.equals(firist)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(firist));
        root.left = preorderDeserialize(nodes);
        root.right = preorderDeserialize(nodes);
        return root;
    }


    /**
     * 后序 - 把字符串反序列化成二叉树
     * @param root
     * @return
     */
    public String postorderSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorderSerialize(root, sb);
        return sb.toString();
    }

    private void postorderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return ;
        }
        postorderSerialize(root.left, sb);
        sb.append(root.val).append(SEP);
        postorderSerialize(root.right, sb);
    }

    /**
     * 后序 - 把字符串反序列化成二叉树
     * @param data
     * @return
     */
    public TreeNode postorderDeserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s: data.split(SEP)) {
            nodes.addLast(s);
        }
        return postorderDeserialize(nodes);
    }

    private TreeNode postorderDeserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) return null;
        //后序 - 反序列化
        String last = nodes.removeLast();
        if (NULL.equals(last)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(last));
        root.right = postorderDeserialize(nodes);
        root.left = postorderDeserialize(nodes);
        return root;
    }

    /**
     * 中序 - 把一棵二叉树序列化成字符串
     * @return
     */
    public String inorderSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        inorderSerialize(root, sb);
        return sb.toString();
    }

    private void inorderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return ;
        }
        inorderSerialize(root.left, sb);
        sb.append(root.val).append(SEP);
        inorderSerialize(root.right, sb);
    }

//    /**
//     * 中序 - 把字符串反序列化成二叉树
//     * @param data
//     * @return
//     */
//    public TreeNode inorderDeserialize(String data) {
//        LinkedList<String> list = new LinkedList<>();
//        for (String s: data.split(SEP)) {
//            list.addLast(s);
//        }
//        return inorderSerialize(list);
//    }
//
//    private TreeNode inorderSerialize(LinkedList<String> nodes) {
//        if (nodes.isEmpty()) return null;
//
//        //中序 反序列化
//        inorderSerialize(nodes);
//    }

    /**
     * 层级 - 序列化
     * @param root
     * @return
     */
    public String levelOrderSerialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            //层级遍历代码
            if (cur == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.val).append(SEP);
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return sb.toString();
    }

    public TreeNode levelOrderDeserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        for (int i=1; i<nodes.length; ) {
            //队列中存的都父节点
            TreeNode parent = q.poll();
            //父节点对应的左侧子节点.
            String left = nodes[i++];
            if (NULL.equals(left)) {
                parent.left = null;
            } else {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            }

            String right = nodes[i++];
            if (NULL.equals(right)) {
                parent.right = null;
            } else {
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
