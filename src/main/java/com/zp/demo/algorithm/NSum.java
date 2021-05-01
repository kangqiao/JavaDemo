package com.zp.demo.algorithm;

import java.util.*;

public class NSum {

    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
    //的三元组。
    //
    // 注意：答案中不可以包含重复的三元组。
    //
    //
    //
    // 示例：
    //
    // 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    //
    //满足要求的三元组集合为：
    //[
    //  [-1, 0, 1],
    //  [-1, -1, 2]
    //]
    //
    // Related Topics 数组 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        if (nums == null || nums.length < 3) return arr;
        Arrays.sort(nums); //排序
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue; //去重
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                if (sum == 0) {
                    arr.add(Arrays.asList(nums[l], nums[r], nums[i]));
                    while (l < r && nums[l] == nums[l + 1]) l++; //去重
                    while (l < r && nums[r] == nums[r - 1]) r--; //去重
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        return arr;
    }

    /**
     * 由出数组nums中等于target的所有数据的组合.
     * 采用盗梦空间的方式, 使用stack记录每一层的数据, 并在判断完后, 移出本层的数据.
     * 当满足target-nums[i] > 0时, 说明还未达到最终目标值, 继续进入下一层空间
     * 当target-nums[i] = 0时, 说明已经命中最终的目标值, 导出stack中数据.
     * 当target-nums[i] < 0时, 说明已经超出最终目标值, nums[i]不合理, 从栈中移出.
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> allSumTarget(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTrace(nums, 0, target, new ArrayDeque<>(), result);
        return result;
    }
    private void backTrace(int[] nums, int start, int target, Deque<Integer> stack, List<List<Integer>> result) {
        for (int i=start; i<nums.length; i++) {
            stack.addLast(nums[i]);
            int tmp = target - nums[i];
            if (tmp > 0) {
                backTrace(nums, i + 1, target - nums[i], stack, result);
            } else if (tmp == 0) {
                result.add(Arrays.asList(stack.toArray(new Integer[]{})));
            }
            stack.removeLast();
        }
    }

    /**
     * 由出数组nums中等于target的所有数据的组合.
     * 判断nums数组中最多有nums.length个数种组合, 最小1个数的组合, 采用降级方案.
     * 即, 采用双指针方式求两数之和, 那么三数之和就可以在两数之和基础上, 计算target-nums[i], 最后将nums[i]加入到结果中.
     * 4数之和, 又可以降级后3数之和, 2数之和. 以此类推.
     * 最后遍历2 ~ nums.length间的所有组合的可能; 1数之和需要单独遍历.
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> allSumTarget1(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                ret.add(list);
            }
        }
        for (int n = 2; n < nums.length - 1; n++) {
            ret.addAll(nSumTarget(nums, n, 0, target));
        }
        return ret;
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = null;
        //至少是2数之和, 且数组大小不应该小于n
        if (n < 2 || sz < n) return res;
        res = new ArrayList<>();
        //2数之和的组合
        if (n == 2) {
            //使用双指针操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) hi--;
                } else {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(left);
                    arr.add(right);
                    res.add(arr);
                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else {
            // n > 2 时, 递归计算(n-1)Sum的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NSum instance = new NSum();
        int[] nums = new int[]{1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10};
        int target = 10;
        List<List<Integer>> ret = instance.allSumTarget(nums, target);

        System.out.println("--------------------------");
        for (List<Integer> list : ret) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> allSumTarget2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTrace2(nums, 0, 0, target, new LinkedList<Integer>(), result);
        return result;
    }

    private void backTrace2(int[] nums, int start, int count, int target, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (start > nums.length - 1) {
            return;
        }
        int ret = count + nums[start];
        if (ret == target) {
            stack.push(nums[start]);
            log("equals start=" + start, stack);
            result.add(Arrays.asList(stack.toArray(new Integer[]{})));
            stack.pop();
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i > start) {
                    swap(nums, i, start);
                    log("swapped: nums[i:"+ i + "]="+ nums[i] + ", nums[start:" + start+"]="+nums[start], null);
                }
                stack.push(nums[start]);
                log("for i=" + i + ", start="+start+", count="+count, stack);
                int ret2 = count + nums[start];
                if (ret2 == target) {
                    log("equals i=" + i + ", start="+start, stack);
                    result.add(Arrays.asList(stack.toArray(new Integer[]{})));
                } else if (ret2 < target){
                    backTrace2(nums, i + 1, count + nums[start], target, stack, result);
                } else {
                    //log("for i=" + i + ", start="+start+", ret2="+ret2, stack);
                }
                stack.pop();
                log("for i=" + i + ", start="+start+", count="+count, stack);
                if (i > start) swap(nums, i, start);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void log(String tag, LinkedList<Integer> stack) {
        System.out.println(tag + ":" + (stack == null? "" : Arrays.asList(stack.toArray(new Integer[]{}))));
    }
}
