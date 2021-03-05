package com.zp.demo.algorithm.dp;

import java.util.Arrays;

/**
 * 516. 最长回文子序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 * 示例 1:
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 * "cbbd"
 * 输出:
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 */
public class LC516最长回文子序列 {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i=0; i<n; i++) {
            dp[i][i] = 1;
        }

        for (int i=n-2; i>=0; i--) {
            for (int j=i+1; j<n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        LC516最长回文子序列 instance = new LC516最长回文子序列();
        String s = "bbbab";
        System.out.println(s + " => " + instance.longestPalindromeSubseq(s));

        s = "aecda";
        System.out.println(s + " => " + instance.longestPalindromeSubseq(s));
    }
}
