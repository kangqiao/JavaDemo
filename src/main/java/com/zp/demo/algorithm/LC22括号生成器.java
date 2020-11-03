package com.zp.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC22括号生成器 {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        _generate(0, 0, n, "", list);
        return list;
    }

    private void _generate(int left, int right, int n, String s, List<String> list) {
        if (left == n && right == n) {
            System.out.println(s);
            list.add(s);
            return;
        }

        if (left < n) {
            _generate(left + 1, right, n, s + "(", list);
        }
        if (left > right) {
            _generate(left, right + 1, n, s + ")", list);
        }

    }

    public static void main(String[] args) {
        System.out.println(new LC22括号生成器().generateParenthesis(3));
    }
}
