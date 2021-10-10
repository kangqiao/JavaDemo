package com.zp.demo.algorithm.model;


public class Node<K, V>{
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K key;
    public V value;
    public Node<K, V> next;
    public Node<K, V> prev;

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}