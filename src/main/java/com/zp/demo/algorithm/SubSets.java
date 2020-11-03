package com.zp.test.leetcode;

//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。
//
// 示例:
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
// Related Topics 位运算 数组 回溯算法


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = new SubSets().subsets(nums);
        for (List<Integer> arr : result) {
            System.out.println(arr);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
