package com.zp.demo.algorithm.dp;

import java.util.Arrays;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 *
 * 输入：nums = [1,5]
 * 输出：10
 */
public class LC312戳气球 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n+2];
        points[0]=points[n+1] = 1;
        for (int i=1; i<=n; i++) {
            points[i] = nums[i-1];
        }

        int[][] dp = new int[n+2][n+2];
        for (int i=n; i>=0; i--) {
            for (int j=i+1; j<n+2; j++) {
                for (int k=i+1; k<j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }

        return dp[0][n+1];
    }

    public static void main(String[] args) {
        LC312戳气球 instance = new LC312戳气球();
        int[] nums;
        nums = new int[]{3,1,5,8};
        System.out.println(Arrays.toString(nums) + "=" + instance.maxCoins(nums));
        nums = new int[]{1,5};
        System.out.println(Arrays.toString(nums) + "=" + instance.maxCoins(nums));
    }
}
