package com.zp.demo.algorithm.simple;

import com.zp.demo.Zlog;

import java.util.Arrays;

/**
 * 一个方法团灭 LeetCode 股票买卖问题
 * https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/tuan-mie-gu-piao-wen-ti
 */
public class LC股票买卖 {

    /**
     * 121. 买卖股票的最佳时机 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * <p>
     * 示例 2:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // 解释：
                // dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][0] = 0;
                //解释：
                // dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                dp[i][1] = -prices[i];
                System.out.print("dp["+i+"]["+0+"]=" + dp[i][0]);
                System.out.println(", dp["+i+"]["+1+"]=" + dp[i][1]);
                continue;
            }
            /**
             * 解释：今天我没有持有股票，有两种可能：
             * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
             * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
             */
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            /**
             * 解释：今天我持有着股票，有两种可能：
             * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
             * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
             */
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            System.out.print("dp["+i+"]["+0+"]=" + dp[i][0]);
            System.out.println(", dp["+i+"]["+1+"]=" + dp[i][1]);
        }
        return dp[n - 1][0];
    }

    /**
     * k=1的优化解法
     * @param prices
     * @return
     */
    public int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * @param prices
     * @return
     */
    public int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
            System.out.print("dp["+i+"]["+0+"]=" + dp_i_0);
            System.out.println(", dp["+i+"]["+1+"]=" + dp_i_1);
        }
        return dp_i_0;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
      * @param prices
     * @return
     */
    public int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; //代表dp[i-2][0]
        for (int i=0; i<n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
            System.out.print("dp["+i+"]["+0+"]=" + dp_i_0);
            System.out.println(", dp["+i+"]["+1+"]=" + dp_i_1);
        }
        return dp_i_0;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * 示例 1:
     * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出: 8
     * 解释: 能够达到的最大利润:
     * 在此处买入prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例1:
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     *
     * 示例 2：
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
     *     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
     *     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * @param prices
     * @return
     */
    public int maxProfit_k_2(int[] prices) {
        int max_k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][max_k+1][2];
        for (int i=0; i<n; i++) {
            for (int k=max_k; k>= 1; k--) {
                if (i-1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n-1][max_k][0];
    }

    /**
     * K = 2的优化版本
     * @param prices
     * @return
     */
    public int maxProfit_k_2_(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price: prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }


    /**
     * 188. 买卖股票的最佳时机 IV
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1：
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     *
     * 示例 2：
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     * @param prices
     * @param max_k
     * @return
     */
    public int maxProfit_k_any(int[] prices, int max_k) {
        int n = prices.length;
        //if (max_k > n / 2) return maxProfit_k_inf(prices);

        int[][][] dp = new int[n][max_k+1][2];
        for (int i=0; i<n; i++) {
            for (int k=max_k; k>=1; k--) {
                if (i-1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    System.out.print("dp["+i+"]["+k+"]["+0+"]=" + dp[i][k][0]);
                    System.out.println(", dp["+i+"]["+k+"]["+1+"]=" + dp[i][k][1]);
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);

                System.out.print("dp["+i+"]["+k+"]["+0+"]=" + dp[i][k][0]);
                System.out.println(", dp["+i+"]["+k+"]["+1+"]=" + dp[i][k][1]);
            }
        }
        return dp[n-1][max_k][0];
    }

    public static void main(String[] args) {
        LC股票买卖 instance = new LC股票买卖();
        Zlog.log("========K = 1=======");
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int ret = instance.maxProfit(prices);
        log(prices, "1", ret);
        ret = instance.maxProfit_k_1(prices);
        log(prices, "1", ret);

        Zlog.log("");
        Zlog.log("========K = +infinity=======");
        prices = new int[]{7, 1, 5, 3, 6, 4};
        ret = instance.maxProfit_k_inf(prices);
        log(prices, "infinity", ret);

        Zlog.log("");
        Zlog.log("========K = +infinity with cooldown=======");
        prices = new int[]{7, 1, 5, 3, 6, 4};
        ret = instance.maxProfit_with_cool(prices);
        log(prices, "infinity with cooldown", ret);
        prices = new int[]{1,2,3,0,2};
        ret = instance.maxProfit_with_cool(prices);
        log(prices, "infinity with cooldown", ret);

        Zlog.log("");
        Zlog.log("========K = +infinity with fee=======");
        prices = new int[]{7, 1, 5, 3, 6, 4};
        ret = instance.maxProfit_with_fee(prices, 2);
        log(prices, "infinity with fee", ret);
        prices = new int[]{1, 3, 2, 8, 4, 9};
        ret = instance.maxProfit_with_fee(prices, 2);
        log(prices, "infinity with fee", ret);

        Zlog.log("");
        Zlog.log("========K = 2======");
        prices = new int[]{3,3,5,0,0,3,1,4};
        ret = instance.maxProfit_k_2(prices);
        log(prices, "2", ret);
        ret = instance.maxProfit_k_2_(prices);
        log(prices, "2_>>>", ret);
        prices = new int[]{1,2,3,4,5};
        ret = instance.maxProfit_k_2(prices);
        log(prices, "2", ret);
        ret = instance.maxProfit_k_2_(prices);
        log(prices, "2_>>>", ret);


        Zlog.log("");
        Zlog.log("========K = any integer=======");
        prices = new int[]{2,4,1};
        int max_k = 2;
        ret = instance.maxProfit_k_any(prices, max_k);
        log(prices, "any integer " + max_k, ret);
        prices = new int[]{3,2,6,5,0,3};
        max_k = 2;
        ret = instance.maxProfit_k_any(prices, max_k);
        log(prices, "any integer " + max_k, ret);
    }

    public static void log(int[] prices, String k, int ret) {
        Zlog.log(new StringBuilder()
                .append("prices: ").append(Arrays.toString(prices))
                .append(", k: ").append(k)
                .append(", ret=>").append(ret)
                .toString());
    }
}
