package com.zp.demo.algorithm.simple;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 一个合法的括号组合的左括号数量一定等于右括号数量.
 * 2. 对一一个合法的括号字符串组合p, 必然对于任何0 <= i < len(p) 都有: 子串p[0..i]中左括号的数量都大于或等于右括号的数量.
 */
public class _4_3回溯_括号生成 {
    public static void main(String[] args) {
        _4_3回溯_括号生成 instance = new _4_3回溯_括号生成();
        print(instance.generateParenthesis(3));
    }

    private static void print(List<String> list) {
        for (String s: list) {
            System.out.print(s + ", ");
        }
    }

    /**
     * 给定一个正整数n, 输出是n对括号的所有合法组合
     * @param n = 3
     * @return "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, n, new ArrayDeque<>(), result);
        return result;
    }

    private static final char[] choices = new char[]{'(', ')'};
    private void backtrack(int left, int right, ArrayDeque<Character> track, List<String> result) {
        //数量小于0肯定是不合法的
        if (left < 0 || right < 0) return ;
        //若左括号剩下的多, 说明不合法
        if (right < left) return;
        //当所有括号都恰好用完时, 得到一个合法的括号组合
        if (left == 0 && right == 0) {
            StringBuilder sb = new StringBuilder();
            for (Character c: track) {
                sb.append(c);
            }
            result.add(sb.toString());
            return ;
        }

        //尝试添加一个左括号
        track.addLast('('); //做选择
        backtrack(left - 1, right, track, result);
        track.removeLast(); //撤销选择

        //尝试添加一个右括号
        track.addLast(')'); //做选择
        backtrack(left, right - 1, track, result);
        track.removeLast(); //撤销选择
    }
}
