package com.zp.demo.algorithm.simple;

import java.util.*;

/**
 * 回溯算法求子集, 组合, 排列问题
 */
public class _4_1回溯算法求子集_组合_排列 {
    public static void main(String[] args) {
        _4_1回溯算法求子集_组合_排列 instance = new _4_1回溯算法求子集_组合_排列();
        List<List<Integer>> result = instance.subsets(new int[]{1,2,3});
        print(result);
        result = instance.subsets1(new int[]{1,2,3});
        print(result);
        result = instance.subsets2(new int[]{1,2,3});
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
            List<Integer> newList = new ArrayList<>();
            newList.addAll(result.get(i));
            newList.add(n);
            result.add(newList);
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        results.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newSubSet = new ArrayList<>();
            for (List<Integer> subset : results) {
                List<Integer> new_subset = new ArrayList<>();
                new_subset.addAll(subset);
                new_subset.add(num);
                newSubSet.add(new_subset);
            }
            results.addAll(newSubSet);
        }
        return results;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) return ans;
        dfs(ans, nums, new ArrayList<Integer>(), 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, ArrayList<Integer> list, int index) {
        //terminator
        if (index == nums.length) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }

        //not pick the number as this index
        dfs(ans, nums, list, index + 1);
        list.add(nums[index]);
        //pick the、 number at this index
        dfs(ans, nums, list, index + 1);

        // reserve state
        list.remove(list.size() - 1);
    }
}
