package com.zp.demo.algorithm.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 单调队列 {
    public static class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();

        public void push(int n) {
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            q.addLast(n);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue q = new MonotonicQueue();
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            if (i < k-1) {
                q.push(nums[i]);
            } else {
                q.push(nums[i]);
                list.add(q.max());
                q.pop(nums[i - k + 1]);
            }
        }
        int[] ret = new int[list.size()];
        for (int i=0; i<list.size(); i++){
            ret[i] = list.get(i);
        }
        return ret;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int max = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (i < k-1) {
                if (nums[i] > max) max = nums[i];
            } else {
                if (nums[i] > max) max = nums[i];
                list.add(max);
                if (max == nums[i - k + 1]) max = nums[i];
            }
        }
        int[] ret = new int[list.size()];
        for (int i=0; i<list.size(); i++){
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        单调队列 instance = new 单调队列();
        int[] nums = new int[]{1,3,-1,-3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(instance.maxSlidingWindow(nums,3)));
        System.out.println(Arrays.toString(instance.maxSlidingWindow2(nums,3)));
    }
}
