package com.zp.demo.algorithm.queue;

import com.zp.demo.algorithm.model.Node;

/**
 * 倒数第n项的求法很简单，只需要两个相差n-1的链表指针一直向前走，当前面的指针的next为null时，
 * 后面走的慢的指针正好是倒数第n个。
 */
public class 单向链表倒数第n项 {

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
        Node node = findNode(node1, 3);

        System.out.println(node);
    }

    public static Node findNode(Node node, int n) {
        if (node == null || node.next == null || n < 0) {
            return node;
        }
        int i = 0;
        Node second = node;
        while (node != null) {
            node = node.next;
            if (++i > n && second != null) {
                second = second.next;
            }
        }
        return second;
    }
}
