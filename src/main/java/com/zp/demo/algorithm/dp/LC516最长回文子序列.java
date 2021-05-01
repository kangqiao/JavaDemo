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

    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i=n-2; i>=0; i--) {
            int pre = 0;
            for (int j=i+1; j<n; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                pre = temp;
            }
        }

        return dp[n-1];
    }


    public String longestPalindrome(String s) {
        int len;
        if (s == null || (len = s.length()) < 2) return s;
        boolean[][] dp = new boolean[len][len];

        //初始化所有长度为1的子串都是回文串
        for (int i=0; i<len; i++) {
            dp[i][i] = true;
        }
        int begin = 0, maxLen = 1;
        //递推开始
        //从右下角往左上角开始枚举.
        for (int i=len-2; i>=0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j-i+1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        LC516最长回文子序列 instance = new LC516最长回文子序列();
        String s = "ac";
        System.out.println(s + " 1=> " + instance.longestPalindromeSubseq(s));
        System.out.println(s + " 2=> " + instance.longestPalindromeSubseq2(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome2(s));

        s = "babad";
        System.out.println(s + " 1=> " + instance.longestPalindromeSubseq(s));
        System.out.println(s + " 2=> " + instance.longestPalindromeSubseq2(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome2(s));

        s = "aecdadce";
        System.out.println(s + " 1=> " + instance.longestPalindromeSubseq(s));
        System.out.println(s + " 2=> " + instance.longestPalindromeSubseq2(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome(s));
        System.out.println(s + " 2=> " + instance.longestPalindrome2(s));
    }
}
