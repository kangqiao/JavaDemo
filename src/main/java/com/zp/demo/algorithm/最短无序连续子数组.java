package com.zp.demo.algorithm;

import java.util.Map;

/**
 * 题目
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 * 分析
 * 如果最右端的一部分已经排好序，这部分的每个数都比它左边的最大值要大，同理，如果最左端的一部分排好序，
 * 这每个数都比它右边的最小值小。所以我们从左往右遍历，如果i位置上的数比它左边部分最大值小，则这个数肯定要排序，
 * 就这样找到右端不用排序的部分，同理找到左端不用排序的部分，它们之间就是需要排序的部分。
 */
public class 最短无序连续子数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        最短无序连续子数组 instance = new 最短无序连续子数组();
        System.out.println(instance.findUnsortedSubarray(nums));
    }

    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int L=0,R=0;
        //从左往右，如果i位置上的数比它左边部分的最大值小，则这个数肯定要排序，遍历到数组尾部，找到右端不用排序的部分。
        for (int i=0; i<len; i++) {
            if (nums[i] < max) {
                R = i;
            }
            max = Math.max(max, nums[i]);
        }

        //从右往左，如果i位置上的数比它右边部分的最小值大，则这个数肯定要排序，遍历到数组头部，找到左端不用排序的部分。
        for (int i=len-1; i>=0; i--) {
            if (nums[i] > min) {
                L = i;
            }
            min = Math.min(min, nums[i]);
        }

        return R > L ? R - L + 1 : 0;
    }

}
