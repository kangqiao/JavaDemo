package com.zp.demo.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class LC46全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrace(nums, 0, res);
        return res;
    }

    public void backTrace(int[] nums, int start, List<List<Integer>> res) {
        if (start >= nums.length) {
            //List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                backTrace(nums, start + 1, res);
                swap(nums, start, i);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        LC46全排列 instance = new LC46全排列();
        int[] nums = new int[]{1, 2, 3};
        log(instance.permute(nums));

        nums = new int[]{0, 1};
        log(instance.permute(nums));

    }

    private static void log(List<List<Integer>> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (List<Integer> list : result) {
            sb.append(Arrays.toString(list.toArray())).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

}
