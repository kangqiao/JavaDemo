package com.zp.demo.algorithm;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
// 示例:
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//
// 说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return res;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "edf");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        search("", digits, 0, res, map);
        return res;
    }

    private void search(String s, String digits, int i, List<String> res, Map<Character, String> map) {

        // terminator 结束条件
        if (i == digits.length()) {
            res.add(s);
            return;
        }
        // process 处理数据
        String letter = map.get(digits.charAt(i));
        for (int j = 0; j < letter.length(); j++) {
            // drill down 递归到下层
            search(s + letter.charAt(j), digits, i+1, res, map);
        }

        // reverse
    }

    public static void main(String[] args) {
        LetterCombinations lc = new LetterCombinations();
        List<String> ret = lc.letterCombinations("23");
        System.out.println(ret);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
