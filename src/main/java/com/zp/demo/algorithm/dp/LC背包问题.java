package com.zp.demo.algorithm.dp;

/**
 * 最基本的背包问题就是01背包问题（01 knapsack problem）：
 * 一共有N件物品，第i（i从1开始）件物品的重量为wt[i]，价值为val[i]。
 * 在总重量不超过背包承载上限W的情况下，能够装入背包的最大价值是多少？
 */
public class LC背包问题 {

    public int knapsack(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N+1][W+1];

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=W; j++) {
                if (j - wt[i-1] < 0) {
                    //说明背包容量不够了, 这种情况下只能选择不装入背包.
                    dp[i][j] = dp[i-1][j];
                } else {
                    //装入或者不装入背包, 择优
                    dp[i][j] = Math.max(dp[i-1][j-wt[i-1]] + val[i-1], dp[i-1][j]);
                }
            }
        }
        return dp[N][W];
    }
}
