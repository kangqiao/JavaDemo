package com.zp.demo.algorithm;

import java.util.Arrays;

public class 比特位计数 {

    public static void main(String[] args) {
        比特位计数 instance = new 比特位计数();
        System.out.println(Arrays.toString(instance.countBits(2)));
        System.out.println(Arrays.toString(instance.countBits(5)));
    }

    private int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        for (int i=0; i<=num; i++) {
            if (i % 2 == 0) {
                //i是偶数时，左边第一位是0，
                // 和i/2所表示的二进制数中的1数量是一致的。
                dp[i] = dp[i/2];
            } else {
                //i是奇数时，i-1是偶数，只需要加1，
                // 即左第一位上补1即可。所以二进制中的1数量要加1。
                dp[i] = dp[i-1] + 1;
            }
        }
        return dp;
    }
}
