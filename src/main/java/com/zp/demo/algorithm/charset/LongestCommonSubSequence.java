package com.zp.demo.algorithm.charset;

//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
//
//
// 若这两个字符串没有公共子序列，则返回 0。
//
//
//
// 示例 1:
//
// 输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace"，它的长度为 3。
//
//
// 示例 2:
//
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
//
//
// 示例 3:
//
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
//
//
//
//
// 提示:
//
//
// 1 <= text1.length <= 1000
// 1 <= text2.length <= 1000
// 输入的字符串只含有小写英文字符。
//
// Related Topics 动态规划
// 👍 209 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0 ) {
            return 0;
        }

        char[] s1 = text1.toCharArray();
        int m = s1.length;
        char[] s2 = text2.toCharArray();
        int n = s2.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i-1] == s2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "ABAZDC";
        String text2 = "BACBAD";
        int ret = new LongestCommonSubSequence().longestCommonSubsequence(text1, text2);
        System.out.println("longestCommonSubsequence="+ret);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
