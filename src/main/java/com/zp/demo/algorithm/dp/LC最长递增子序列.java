package com.zp.demo.algorithm.dp;

import com.zp.demo.Zlog;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LC最长递增子序列 {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            //dp判断公式
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int step = 0;
        for (int i = 0; i < dp.length; i++) {
            step = Math.max(step, dp[i]);
        }
        return step;
    }

    public int lengthOfLIS2(int[] nums) {
       int[] top = new int[nums.length];
       int piles = 0;

       for (int i = 0; i < nums.length; i++) {
           //取出一张扑克牌
           int poker = nums[i];

           //插入堆中.
           int left = 0, right = piles;
           while (left < right) {
               int mid = (left + right) / 2;
               if (top[mid] < poker) {
                   left = mid + 1;
               } else {
                   right = mid;
               }
           }

           if (left == piles) piles++;
           top[left] = poker;
       }

       return piles;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        //排序; 以宽升序排列; 当宽相等时, 列按降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1]: o1[0] - o2[0];
            }
        });

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i=0; i<n; i++){
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public int maxEnvelopes2(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] top = new int[envelopes.length];
        int piles = 0;

        for (int i = 0; i < envelopes.length; i++) {
            int picker = envelopes[i][1];

            int left = 0, right = piles;
            while (left < right) {
                int mid = (left+right) / 2;
                if (top[mid] < picker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left == piles) piles++;
            top[left] = picker;
        }

        return piles;
    }

    public static void main(String[] args) {
        LC最长递增子序列 instance = new LC最长递增子序列();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        Zlog.log(instance.lengthOfLIS(nums) + "");
        Zlog.log(instance.lengthOfLIS2(nums) + "");
        nums = new int[]{0, 1, 0, 3, 2, 3};
        Zlog.log(instance.lengthOfLIS(nums) + "");
        Zlog.log(instance.lengthOfLIS2(nums) + "");
        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        Zlog.log(instance.lengthOfLIS(nums) + "");
        Zlog.log(instance.lengthOfLIS2(nums) + "");

        int[][] envelopoes = new int[][]{{5,4},{6,4},{6,7},{1,8},{2,3},{5,2}};
        Zlog.log(instance.maxEnvelopes(envelopoes) + "");
        Zlog.log(instance.maxEnvelopes2(envelopoes) + "");
    }

}
