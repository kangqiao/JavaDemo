package com.zp.demo.algorithm.dp;

//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
//
//
//
//
//
//
// 示例 1:
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2:
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
//
//
// 示例 3:
//
// 输入: amount = 10, coins = [10]
//输出: 1
//
//
//
//
// 注意:
//
// 你可以假设：
//
//
// 0 <= amount (总金额) <= 5000
// 1 <= coin (硬币面额) <= 5000
// 硬币种类不超过 500 种
// 结果符合 32 位符号整数
//
// 👍 280 👎 0


import com.zp.demo.Zlog;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
public class LCM518零钱兑换2 {

    public int coinChange(int amount, int[] coins) {
        final int INVALID_MAX_VALUE = amount + 1;
        int[] dp = new int[INVALID_MAX_VALUE];
        Arrays.fill(dp, INVALID_MAX_VALUE);

        dp[0] = 0;
        //外层for循环遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            //内层for循环所有选择的最小值
            for (int coin: coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return (dp[amount] == INVALID_MAX_VALUE)? -1: dp[amount];
    }

    public int change(int amount, int[] coins) {
        Map<Integer, Integer> map = new HashMap<>();
        return dp(amount, coins, map);
    }

    private int dp(int n, int[] coins, Map<Integer, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        if (n == 0) return 0;
        if (n < 0) return -1;

        int ret = Integer.MAX_VALUE;
        for (int coin: coins) {
            int subProblem = dp(n - coin, coins, map);
            if (subProblem == -1) continue;
            ret = Math.min(ret, 1+subProblem);
        }
        map.put(n, ret != Integer.MAX_VALUE? ret: -1);
        return map.get(n);
    }

    public static void main(String[] args) {
        LCM518零钱兑换2 instance = new LCM518零钱兑换2();
        int[] coins = new int[]{1,2,5};
        Zlog.log(instance.coinChange(5, coins) + "");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
