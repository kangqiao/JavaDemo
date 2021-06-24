package com.zp.demo.algorithm.simple;


import java.util.*;

public class _4_6_NSum {

    /**
     * 求nums数组中满足和等于target的一个组合。
     */
    public int[] twoSum(int[] nums, int target) {
        //先对数组排序
        Arrays.sort(nums);
        //左右指针
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            } else {
                return new int[]{nums[lo], nums[hi]};
            }
        }
        return new int[]{};
    }

    /**
     * 求nums数组中满足和等于target的所有组合（不重复）
     * 例如nums=[1,3,1,2,2,3] target = 4 算法返回[[1,3],[2,2]]
     */
    public List<List<Integer>> twoSumTarget(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            //根据sum和target的比较，移动左、右指针
            int left = nums[lo], right = nums[hi];
            if (sum < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right) hi--;
            } else {
                res.add(Arrays.asList(nums[lo], nums[hi]));
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
        return res;
    }

    /**
     * 利用twoSumTarget计算thressSumTarget
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSumTarget(int[] nums, int target) {
        //数组排序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //穷举threeSum的第一个数
        for (int i=0; i<n; i++) {
            List<List<Integer>> tuples = twoSumTargetForThree(nums, i+1, target - nums[i]);
            //如果存在满足条件的二元组，再加上nums[i]就是结果三元组
            for (List<Integer> tuple: tuples) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            //跳过第一个数字重复的情况，否则会出现重复结果
            while (i < n - 1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }

    //为threeSumTarget计算剩余的twoSum集合。
    private List<List<Integer>> twoSumTargetForThree(int[] nums, int start, int target) {
        int lo = start, hi = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            //根据sum和target的比较，移动左、右指针
            int left = nums[lo], right = nums[hi];
            if (sum < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right) hi--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[lo]);
                list.add(nums[hi]);
                res.add(list);
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
        return res;
    }


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
    private void backTrace(int[] nums, int start, int target, Deque<Integer> trace, List<List<Integer>> result) {
        if (target < 0) return ;
        if (target == 0) {
            result.add(Arrays.asList(trace.toArray(new Integer[]{})));
        }
        for (int i=start; i<nums.length; i++) {
            trace.addLast(nums[i]);
            backTrace(nums, i + 1, target - nums[i], trace, result);
            trace.removeLast();
        }
    }
    private void backTrace2(int[] nums, int start, int target, Deque<Integer> stack, List<List<Integer>> result) {
        for (int i=start; i<nums.length; i++) {
            stack.addLast(nums[i]);
            int tmp = target - nums[i];
            if (tmp > 0) {
                backTrace(nums, i + 1, tmp, stack, result);
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

    /**
     * NSum的解决方案。*******
     * @param nums 源数组
     * @param n 要求出的几个数之和
     * @param start 从start下标开始统计
     * @param target 目标数
     * @return
     */
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
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
        _4_6_NSum instance = new _4_6_NSum();
        int[] nums = new int[]{1, 2, 3, 4, 0, 5, 6};
        int target = 6;
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
