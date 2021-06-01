package com.zp.demo.algorithm.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int x, ListNode node) {
        val = x;
        next = node;
    }
}