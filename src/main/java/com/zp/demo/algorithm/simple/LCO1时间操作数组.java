package com.zp.demo.algorithm.simple;

import com.zp.demo.Zlog;

import java.util.*;

public class LCO1时间操作数组 {


    /**
     * 380. 常数时间插入、删除和获取随机元素
     * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
     *     insert(val)：当元素 val 不存在时，向集合中插入该项。
     *     remove(val)：元素 val 存在时，从集合中移除该项。
     *     getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
     *
     * 示例 :
     * // 初始化一个空的集合。
     * RandomizedSet randomSet = new RandomizedSet();
     * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
     * randomSet.insert(1);
     * // 返回 false ，表示集合中不存在 2 。
     * randomSet.remove(2);
     * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
     * randomSet.insert(2);
     * // getRandom 应随机返回 1 或 2 。
     * randomSet.getRandom();
     * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
     * randomSet.remove(1);
     * // 2 已在集合中，所以返回 false 。
     * randomSet.insert(2);
     * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
     * randomSet.getRandom();
     */
    public static class RandomizedSet {

        List<Integer> nums;
        Map<Integer, Integer> valToIndex;
        Random random;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (valToIndex.containsKey(val)) {
                return false;
            }
            valToIndex.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            int index = valToIndex.get(val);
            int last = nums.get(nums.size()-1);
            valToIndex.put(last, index);
            nums.set(index, last);
            nums.remove(nums.size()-1);
            valToIndex.remove(val);
            return true;
        }


        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }

        /**
         * Your RandomizedSet object will be instantiated and called as such:
         * RandomizedSet obj = new RandomizedSet();
         * boolean param_1 = obj.insert(val);
         * boolean param_2 = obj.remove(val);
         * int param_3 = obj.getRandom();
         */
        public static void test() {
            // 初始化一个空的集合。
            RandomizedSet randomSet = new RandomizedSet();
            // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
            Zlog.log("randomSet.insert(1) = " + randomSet.insert(1));
            // 返回 false ，表示集合中不存在 2 。
            Zlog.log("randomSet.remove(2) = " + randomSet.remove(2));
            // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
            Zlog.log("randomSet.insert(2) = " + randomSet.insert(2));
            // getRandom 应随机返回 1 或 2 。
            Zlog.log("randomSet.getRandom() = " + randomSet.getRandom());
            // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
            Zlog.log("randomSet.remove(1) = " + randomSet.remove(1));
            // 2 已在集合中，所以返回 false 。
            Zlog.log("randomSet.insert(2) = " + randomSet.insert(2));
            // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
            Zlog.log("randomSet.getRandom() = " + randomSet.getRandom());
        }

    }

    /**
     * 710. 黑名单中的随机数
     * 给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。
     * 对它进行优化使其尽量少调用系统方法 Math.random() 。
     * 提示:
     *     1 <= N <= 1000000000
     *     0 <= B.length < min(100000, N)
     *     [0, N) 不包含 N，详细参见 interval notation 。
     *
     * pick 函数会被多次调用，每次调用都要在区间 [0,N) 中「等概率随机」返回一个「不在 blacklist 中」的整数。
     * 这应该不难理解吧，比如给你输入 N = 5, blacklist = [1,3]，那么多次调用 pick 函数，会等概率随机返回 0, 2, 4 中的某一个数字。
     */
    public static class Solution {

        //随机数的最范围
        int sz;
        //将黑名单中的数值映射到[0, N)区间数组的最后面; 这样, 中果随机到黑名单中的数据时, 直接返回其映射的最后的下标就可以了.
        Map<Integer, Integer> mapping;
        Random random = new Random();
        public Solution(int N, int[] blacklist) {
            sz = N - blacklist.length;
            mapping = new HashMap<>();

            for (int b: blacklist) {
                mapping.put(b, 666);
            }

            int last = N - 1;
            for (int b: blacklist) {
                //如果b已经在区间[sz, N), 可以直接忽略
                if (b >= sz) continue;

                //说明不在区间[sz, N), 则需要将b(黑名单)插入到最后的位置.
                //判断last位置是否已被占用, 从后往前轮询直到未被占用的last
                while (mapping.containsKey(last)) last--;

                mapping.put(b, last);
                last--;
            }
        }

        public int pick() {
            //随机选取一个索引
            int index = random.nextInt(sz);
            //这个索引命中了黑名单, 需要被映射到其他位置.
            if (mapping.containsKey(index)) return mapping.get(index);
            //若没有命中黑名单, 则直接返回.
            return index;
        }

        /**
         * Your Solution object will be instantiated and called as such:
         * Solution obj = new Solution(N, blacklist);
         * int param_1 = obj.pick();
         */
        public static void test() {

        }
    }

    public static void main(String[] args) {
        RandomizedSet.test();
    }

}
