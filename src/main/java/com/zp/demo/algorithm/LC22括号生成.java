package com.zp.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LC22括号生成 {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(0, 0, n, "", list);
        return list;
    }
    private void generate(int left, int right, int n, String s, List<String> list) {
        if (left == n && right == n) {
            list.add(s);
            return;
        }
        if (left < n) {
            generate(left + 1, right, n, s + "(", list);
        }
        if (right < left) {
            generate(left, right + 1, n, s + ")", list);
        }
    }

    public static void main(String[] args) {

        List<String> list = new LC22括号生成().generateParenthesis(4);
        for (String s: list) {
            System.out.println(s);
        }
    }
}
