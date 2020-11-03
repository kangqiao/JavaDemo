package com.zp.demo.algorithm;

import java.util.HashMap;

class LRUCache{

    /**
     * @param args
     * 测试程序，访问顺序为[[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]，其中成对的数调用put，单个数调用get。
     * get的结果为[1],[-1],[-1],[3],[4]，-1表示缓存未命中，其它数字表示命中。
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


    static final class Entry<K, V>{
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