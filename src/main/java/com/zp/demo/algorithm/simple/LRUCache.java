package com.zp.demo.algorithm.simple;

import com.zp.demo.algorithm.model.Node;

import java.util.HashMap;

public class LRUCache<K, V> {


    private final Node<K, V> head, tail;
    private final HashMap<K, Node<K, V>> map;
    private final int capacity;

    public LRUCache(int capacity) {
        //初始化双向链表的数据
        this.map = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            //移动到链表尾部, 表示最新访问.
            popToTail(node);
            return node.value;
        }
        return null;
    }

    public void put(K key, V val) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = val;
            popToTail(node);
        } else {
            Node<K, V> newNode = new Node<>(key, val);
            if (map.size() >= capacity) {
                //删除链表头
                Node<K, V> first = removeFirst();
                map.remove(first.key);
            }
            addToTail(newNode);
            map.put(key, newNode);
        }
    }

    private void addToTail(Node<K, V> node) {
        Node<K, V> last = tail.prev;
        node.next = tail;
        node.prev = last;
        last.next = node;
        tail.prev = node;
    }

    private Node<K, V> removeFirst() {
        Node<K, V> first = head.next;
        Node<K, V> second = first.next;
        head.next = second;
        second.prev = head;
        return first;
    }

    private void popToTail(Node<K, V> node) {
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;
        prev.next = next;
        next.prev = prev;
        addToTail(node);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
