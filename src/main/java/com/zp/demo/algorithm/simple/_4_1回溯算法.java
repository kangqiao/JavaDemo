package com.zp.demo.algorithm.simple;

import java.util.*;

/**
 * 回溯算法求子集, 组合, 排列问题
 */
public class _4_1回溯算法 {
    public static void main(String[] args) {
        _4_1回溯算法 instance = new _4_1回溯算法();
        List<List<Integer>> result = instance.subsets(new int[]{1,2,3});
        print(result);
    }

    public static void print(List<List<Integer>> list) {
        System.out.println();
        for (List<Integer> l: list) {
            System.out.print(l.toString());
            System.out.print(",");
        }
    }

    /**
     * 求子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int n: nums) {
            stack.push(n);
        }
        List<List<Integer>> result = new ArrayList<>();
        subset(stack, result);
        return result;
    }

    private void subset(LinkedList<Integer> stack, List<List<Integer>> result) {
        if (stack.isEmpty()) {
            result.add(new ArrayList<>());
            return;
        }
        int n = stack.pop();
        subset(stack, result);
        int size = result.size();
        for (int i=0; i<size; i++) {
            result.add(addAndToList(result.get(i), n));
        }
    }

    private List<Integer> addAndToList(List<Integer> list, int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i: list) {
            ret.add(i);
        }
        ret.add(n);
        return ret;
    }
}
