package com.zp.demo.algorithm.charset;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 16.20. T9键盘
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
 *
 * 示例 1:
 *
 * 输入: num = "8733", words = ["tree", "used"]
 * 输出: ["tree", "used"]
 * 示例 2:
 *
 * 输入: num = "2", words = ["a", "b", "c", "d"]
 * 输出: ["a", "b", "c"]
 * 提示：
 *
 * num.length <= 1000
 * words.length <= 500
 * words[i].length == num.length
 * num中不会出现 0, 1 这两个数字
 */
public class LCT9键盘 {


    public List<String> getValidT9Words(String num, String[] words) {
        // 建立字母对数字的映射
        int[] map = new int[]{ '2','2','2','3','3','3','4','4','4','5','5','5','6','6','6','7','7','7','7','8','8','8','9','9','9','9' };

        List<String> res = new ArrayList<>();
        for (String word: words) {
            boolean isValid = true;
            for (int i = 0; i < num.length(); i++) {
                int f = word.charAt(i) - 'a';
                if (f<0 || f >= map.length || map[f] != num.charAt(i)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) res.add(word);
        }
        return res;
    }
}
