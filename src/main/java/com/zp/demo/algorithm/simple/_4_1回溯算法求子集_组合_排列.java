package com.zp.demo.algorithm.simple;


import java.util.*;

/**
 * 回溯算法求子集, 组合, 排列问题
 */
public class _4_1回溯算法求子集_组合_排列 {
    public static void main(String[] args) {
        _4_1回溯算法求子集_组合_排列 instance = new _4_1回溯算法求子集_组合_排列();
        //子集
        print(instance.subsets3(new int[]{1,2,3}));

        //组合
        print(instance.combine(4, 2));

        //排列
        print(instance.permute(new int[]{1,2,3, 1}));
    }

    public static void print(List<List<Integer>> list) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> l: list) {
            sb.append(l.toString()).append(",");
        }
        sb.deleteCharAt(sb.length()-1).append("]");
        System.out.println(sb.toString());
    }

    /**
     * 排列(回溯法)
     * 子集和组合都需要一个start变量防止重复, 而排列问题不需要防止重复, 所以不需要start变量.
     * @param nums [1,2,3]
     * @return [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTracePermute(nums, new ArrayDeque<>(), result);
        return result;
    }

    private void backTracePermute(int[] nums, ArrayDeque<Integer> trace, List<List<Integer>> result) {
        if (trace.size() == nums.length) {
            result.add(Arrays.asList(trace.toArray(new Integer[]{})));
            return ;
        }
        for (int i=0; i<nums.length; i++) {
            //排除不合法的选择
            if (trace.contains(nums[i])) continue;
            trace.addLast(nums[i]);
            backTracePermute(nums, trace, result);
            trace.removeLast();
        }
    }

    /**
     * 组合 (回溯法)
     * 求[1..n]中k个数字的所有组合
     * 例如输入n=4, k=2  => [[1,2],[1,3],[1,4],[2,3],[2,4][3,4]]
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backTraceCombine(n, 1, k, new ArrayDeque<>(), result);
        return result;
    }

    private void backTraceCombine(int n, int start, int k, ArrayDeque<Integer> trace, List<List<Integer>> result) {
        if (k==trace.size()) {
            result.add(Arrays.asList(trace.toArray(new Integer[]{})));
            return ;
        }
        for (int i=start; i<=n; i++) {
            trace.addLast(i);
            backTraceCombine(n, i+1, k, trace, result);
            trace.removeLast();
        }
    }

    /**
     * 求子集(回溯法)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrace(nums, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void backTrace(int[] nums, int start, ArrayDeque<Integer> trace, List<List<Integer>> result) {
        result.add(Arrays.asList(trace.toArray(new Integer[]{})));
        for (int i=start; i<nums.length; i++) {
            trace.addLast(nums[i]);
            backTrace(nums, i+1, trace, result);
            trace.removeLast();
        }
    }

    /**
     * 求子集(数学归纳法)
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

    /**
     * 求子集1
     * @param nums
     * @return
     */
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

    /**
     * 求子集2
     * @param nums
     * @return
     */
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
