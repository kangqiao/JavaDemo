package com.zp.demo.algorithm.tree;


import com.zp.demo.Zlog;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://www.nowcoder.com/practice/1b0b7f371eae4204bc4a7570c84c2de1?tpId=117&&tqId=34937&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
 * 给定一棵二叉树，判断琪是否是自身的镜像（即：是否对称）
 * 例如：下面这棵二叉树是对称的
 *      1
 *    /  \
 *   2    2
 *  / \  / \
 * 3  4 4   3
 * 下面这棵二叉树不对称。
 *    1
 *   / \
 *  2   2
 *   \   \
 *    3   3
 * 备注：
 * 希望你可以用递归和迭代两种方法解决这个问题
 * 示例1
 * 输入
 * {1,2,2}
 * 返回值
 * true
 * <p>
 * 示例2
 * 输入
 * 复制
 * {1,2,3,3,#,2,#}
 * 返回值
 * 复制
 * false
 * <p>
 * 解题答案: https://mp.weixin.qq.com/s/GoazAB2M6PobOzPInch-KA
 */
public class 对称二叉树 {

    //递归实现
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        //从两个子节点开始判断
        return recur(root.left, root.right);
    }

    private static boolean recur(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null) return true;
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) return false;
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    //对列实现
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root.left);
        queue.push(root.right);

        while (!queue.isEmpty()) {
            //每两个出队
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            //如果都为空继续循环
            if (left == null && right == null) continue;
            //如果一个为空一个不为空，说明不是对称的，直接返回false
            if (left == null ^ right == null) return false;
            //如果这两个值不相同，也不是对称的，直接返回false
            if (left.val != right.val) return false;

            //这里要记住入队的顺序，他会每两个两个的出队。
            //左子节点的左子节点和右子节点的右子节点同时入队，因为他俩会同时比较。
            //左子节点的右子节点和右子节点的左子节点同时入队，因为他俩会同时比较。
            queue.push(left.left);
            queue.push(right.right);
            queue.push(left.right);
            queue.push(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] test0 = new int[]{};
        int[] test1 = new int[]{1, 2, 2, 3, 4, 4, 3};
        int[] test2 = new int[]{1, 2, 2, '#', 3, '#', 3};

        TreeNode root = BinaryTree.createTree(test0, 0);
        Zlog.log(Arrays.toString(test0) + "=> isSymmetric=" + isSymmetric(root));
        Zlog.log(Arrays.toString(test0) + "=> isSymmetric2=" + isSymmetric2(root));

        root = BinaryTree.createTree(test1, 0);
        Zlog.log(Arrays.toString(test1) + "=> isSymmetric=" + isSymmetric(root));
        Zlog.log(Arrays.toString(test1) + "=> isSymmetric2=" + isSymmetric2(root));

        root = BinaryTree.createTree(test2, 0);
        Zlog.log(Arrays.toString(test2) + "=> isSymmetric=" + isSymmetric(root));
        Zlog.log(Arrays.toString(test2) + "=> isSymmetric2=" + isSymmetric2(root));
    }
}
