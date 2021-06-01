package com.zp.demo.algorithm.simple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 单调栈 {

    public int[] nextGreaterElement(int[] nums) {
        int[] ans = new int[nums.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return ans;
    }

    public int[] dailyTemperatues(int[] t) {
        int[] ans = new int[t.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = t.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && t[stack.peek()] <= t[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 2*n-1; i>=0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i%n]) {
                stack.pop();
            }
            //利用%求模防止索引越界
            ans[i%n] = stack.isEmpty()? -1: stack.peek();
            stack.push(nums[i%n]);
        }
        return ans;
    }

    public static void main(String[] args) {
        单调栈 instance = new 单调栈();
        int[] nums = new int[]{2, 1, 2, 4, 3};
        System.out.println(Arrays.toString(instance.nextGreaterElement(nums)));
        int[] t = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(instance.dailyTemperatues(t)));
        System.out.println(Arrays.toString(instance.nextGreaterElements(nums)));
    }
}
