package com.zp.demo.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * <p>
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
class LRUCache2<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;
    public LRUCache2(int cacheSize) {
        super(16, 0.75f,  true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    /**
     * > Task :LRUCache2.main()
     * 1
     * null
     * null
     * 3
     * 4
     * @param args
     */
    public static void main(String[] args) {
        LRUCache2<Integer, Integer> cache = new LRUCache2<>(2);
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

public class LRUCache {

    /**
     * @param args 测试程序，访问顺序为[[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]，其中成对的数调用put，单个数调用get。
     *             get的结果为[1],[-1],[-1],[3],[4]，-1表示缓存未命中，其它数字表示命中。
     */
    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);
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


    static final class Entry<K, V> {
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K key;
        V value;
        Entry<K, V> next;
        Entry<K, V> prev;
    }

    private final int capacity;
    private final Entry<Integer, Integer> head;
    private final Entry<Integer, Integer> tail;
    private final HashMap<Integer, Entry<Integer, Integer>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Entry<Integer, Integer>(null, 0);
        tail = new Entry<Integer, Integer>(null, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Entry<Integer, Integer> entry = map.get(key);
            //移动到链表尾部, 表示最新访问.
            popToTail(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Entry<Integer, Integer> entry = map.get(key);
            entry.value = value;
            popToTail(entry);
        } else {
            Entry<Integer, Integer> newEntry = new Entry<Integer, Integer>(key, value);
            if (map.size() >= capacity) {
                //超过容量, 删除最久访问的元素.
                Entry<Integer, Integer> first = removeFirst();
                map.remove(first.key);
            }
            addToTail(newEntry);
            map.put(key, newEntry);
        }
    }

    //将entry添加到链表尾部
    private void addToTail(Entry<Integer, Integer> entry) {
        Entry<Integer, Integer> last = tail.prev;
        entry.next = tail;
        entry.prev = last;
        last.next = entry;
        tail.prev = entry;
    }

    private Entry<Integer, Integer> removeFirst() {
        Entry<Integer, Integer> first = head.next;
        Entry<Integer, Integer> second = first.next;
        head.next = second;
        second.prev = head;
        return first;
    }

    //将entry 移动到链表末端
    private void popToTail(Entry<Integer, Integer> entry) {
        Entry<Integer, Integer> next = entry.next;
        Entry<Integer, Integer> prev = entry.prev;
        prev.next = next;
        next.prev = prev;
        Entry<Integer, Integer> last = tail.prev;
        last.next = entry;
        tail.prev = entry;
        entry.prev = last;
        entry.next = tail;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */