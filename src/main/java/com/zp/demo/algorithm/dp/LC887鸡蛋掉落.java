package com.zp.demo.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它扔下去。如果某个蛋扔下后没有摔碎，则可以将蛋捡起重复使用。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 */
public class LC887鸡蛋掉落 {
    /**
     * dp数组的高级解法
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        int m = 0;
        while (dp[k][m] < n) {
            m++;
            for (int i=1; i<=k; i++) {
                dp[i][m] = 1 + dp[i][m-1] + dp[i-1][m-1];
            }
        }
        return m;
    }

    /**
     * dp递归函数初级解法
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop1(int k, int n) {
        return dp1(k, n, new HashMap<String, Integer>());
    }

    //dp递归函数
    private int dp1(int k, int n, Map<String, Integer> memo) {
        if (k == 1) return n; //只剩一个鸡蛋了, 从下往上遍历, 最坏要n次.
        if (n == 0) return 0; //已经到最底层了, 返回0找到了.
        String key = k+">"+n;
        if (memo.containsKey(key)) return memo.get(key);

        int res = n;
        //穷举所有可能的选择
        for (int i=1; i<=n; i++) {
            //递归穷举两种情况下最坏的可能性=> 碎了(k-1,n=i-1)遍历i层之下; 没碎(k, n-i)遍历i层之上的所有可能性;
            int resI = Math.max(dp1(k-1, i-1, memo), dp1(k, n-i, memo)) + 1; //因为在i层扔了一次, 所以此次+1;
            res = Math.min(res, resI);
        }
        //记入备忘录
        memo.put(key, res);
        return res;
    }

    /**
     * dp递归函数中级解法
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop2(int k, int n) {
        return dp2(k, n, new HashMap<String, Integer>());
    }

    //dp递归函数 superEggDrop2
    private int dp2(int k, int n, Map<String, Integer> memo) {
        if (k == 1) return n; //只剩一个鸡蛋了, 从下往上遍历, 最坏要n次.
        if (n == 0) return 0; //已经到最底层了, 返回0找到了.
        String key = k+">"+n;
        if (memo.containsKey(key)) return memo.get(key);

        int res = n;
        int l = 1, r = n;
        while (l <= r) {
            int mid = (l + r) / 2;
            //在mid层如果碎了, 穷举mid-1层之下的所有可能性.
            int broken = dp2(k - 1, mid - 1, memo);
            //在mid层如果没碎, 穷举mid+1层之上的所有可能性.
            int not_broken = dp2(k, n - mid, memo);
            //res = min(max(碎, 没碎) + 1)
            if (broken > not_broken) {
                r = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                l = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }
        memo.put(key, res);
        return res;
    }

    public static void main(String[] args) {
        LC887鸡蛋掉落 instance = new LC887鸡蛋掉落();
        int k = 0, n = 0;
        k = 2;
        n = 10;
        System.out.println("superEggDrop2(k:" + k + ", n:" + n + ") = " + instance.superEggDrop2(k, n));
        System.out.println("superEggDrop(k:" + k + ", n:" + n + ") = " + instance.superEggDrop(k, n));
        k = 2;
        n = 6;
        System.out.println("superEggDrop2(k:" + k + ", n:" + n + ") = " + instance.superEggDrop2(k, n));
        System.out.println("superEggDrop(k:" + k + ", n:" + n + ") = " + instance.superEggDrop(k, n));
        k = 3;
        n = 14;
        System.out.println("superEggDrop2(k:" + k + ", n:" + n + ") = " + instance.superEggDrop2(k, n));
        System.out.println("superEggDrop(k:" + k + ", n:" + n + ") = " + instance.superEggDrop(k, n));
        k = 4;
        n = 5000;
        //System.out.println("superEggDrop1(k:" + k + ", n:" + n + ") = " + instance.superEggDrop1(k, n));
        System.out.println("superEggDrop2(k:" + k + ", n:" + n + ") = " + instance.superEggDrop2(k, n));
        System.out.println("superEggDrop(k:" + k + ", n:" + n + ") = " + instance.superEggDrop(k, n));

    }
}
