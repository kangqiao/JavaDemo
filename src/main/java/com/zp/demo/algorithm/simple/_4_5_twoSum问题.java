package com.zp.demo.algorithm.simple;

import java.util.*;

public class _4_5_twoSum问题 {


    static class TwoSum {
        Map<Integer, Integer> freq = new HashMap<>();
        //向数据结构中添加一个数number
        public void add(int number) {
            //记录number出现的次数
            freq.put(number, freq.getOrDefault(number, 0) + 1);
        }

        //寻找当前数据结构中是否存在两个数的和为value
        public boolean find(int value) {
            for (Integer key: freq.keySet()) {
                int other = value - key;
                //情况一(重复) add了[3,3,2,5]之后，执行find(6)，由于3出现了两次，3+3=6，所有返回true;
                if (other == key && freq.get(key) > 1) return true;
                //情况二(不重复) add了[3,3,2,5]之后，执行find(7)，那么key为2，other为5时算法可以返回true;
                if (other != key && freq.containsKey(other)) return true;
            }
            return false;
        }

        //如果find方法使用非常频繁，优化后
        Set<Integer> sum = new HashSet<>();
        List<Integer> nums = new ArrayList<>();
        public void add2(int number) {
            //记录所有可能组成的和
            for (int n: nums) {
                sum.add(n+number);
            }
            nums.add(number);
        }

        public boolean find2(int value) {
            return sum.contains(value);
        }

    }

    public static void main(String[] args) {
        _4_5_twoSum问题 instance = new _4_5_twoSum问题();
        int[] nums = new int[]{3,1,3,6};
        print(instance.twoSum(nums, 6));
    }

    private static void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    //针对nums已经排好序（从小到大）的情况
    public int[] twoSumBySortNums(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    //空间缓存余数的下标。
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            index.put(nums[i], i);
        }
        for (int i=0; i<nums.length; i++) {
            int other = target - nums[i];
            if (index.containsKey(other) && index.get(other) != i) {
                return new int[]{i, index.get(other)};
            }
        }
        return new int[]{-1, -1};
    }

    //暴力求解
    public int[] twoSum1(int[] nums, int target) {
        //穷举这两个数的所有可能
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
