package com.zp.demo.algorithm.dp;

import java.util.HashMap;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 *
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 *
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 */
public class LC10正则表达式匹配 {

    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0, new HashMap<String, Boolean>());
    }

    private boolean dp(String s, int i, String p, int j, HashMap<String, Boolean> memo) {
        int m = s.length(), n = p.length();

        //base case
        if (j == n) return i == m;

        if (i == m) {
            if ((n - j) % 2 == 1) return false;
            //检查是否为x*y*z*这种形式
            for (; j+1 < n; j+=2) {
                if (p.charAt(j+1) != '*') return false;
            }
            return true;
        }

        //记录状态(i, j) 清除重叠子问题
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res =  dp(s, i, p, j + 2, memo) || dp(s, i + 1, p, j, memo);
            } else {
                res = dp(s, i + 1, p, j + 1, memo);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2, memo);
            } else {
                res = false;
            }
        }

        memo.put(key, res);

        return res;
    }

    public static void main(String[] args) {
        LC10正则表达式匹配 instance = new LC10正则表达式匹配();
        String s = "", p = "";
        s = "aa"; p = "a";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "aa"; p = "a*";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "ab"; p = ".*c";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "ab"; p = ".*";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "aaa"; p = "a*a";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "aab"; p = "c*a*b";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

        s = "mississippi"; p = "mis*is*ip*.";
        System.out.println("\"" + s + "\".isMatch(\"" + p + "\") = " + instance.isMatch(s, p));

    }
}
