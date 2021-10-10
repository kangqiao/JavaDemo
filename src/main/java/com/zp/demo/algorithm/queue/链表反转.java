package com.zp.demo.algorithm.queue;

import com.zp.demo.algorithm.model.Node;

public class 链表反转 {
    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);
        Node node7 = new Node(7, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;
//        Node newnode = reverse1(node1);
        Node newnode = reverse2(node1, null);

        while (newnode != null) {
            System.out.println(newnode.key);
            newnode = newnode.next;
        }
    }

    //非递归算法的思路，把前一个节点指向当前节点的下一个节点。
    public static Node reverse1(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node cur = node;
        Node pre = null;
        Node next = null;

        while (cur != null) {
            next = cur.next; //下一个取出
            cur.next = pre; //变换当前的下一个为pre
            pre = cur; //更新pre为当前
            cur = next; //更新cur为下一个
        }

        return pre;
    }

    // 递归算法的思路类似，找到最后一个节点，它的next等于null，则把前一个节点作为它的next节点。
    public static Node reverse2(Node node, Node prev) {
        if (node == null) {
            return prev;
        } else if (node.next == null) {
            node.next = prev;
            return node;
        } else {
            Node re = reverse2(node.next, node);
            node.next = prev;
            return re;
        }
    }
}
