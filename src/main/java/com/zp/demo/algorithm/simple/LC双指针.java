package com.zp.demo.algorithm.simple;
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 105
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 899 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
import com.zp.demo.Zlog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class LC双指针 {

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 反转数组
     * @param nums
     */
    public void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++; right--;
        }
    }

    /**
     * 最小覆盖子串
     * LeetCode 76 难
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c: t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
        int left = 0, right = 0;
        int valid = 0;
        //记录最小覆盖子串的超始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            //开始滑动
            // c是将入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            //进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) valid++;
            }

            //判断左侧窗口是否要收缩
            while (valid == need.size()) {
                //在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d是将移出窗口的字符
                char d = s.charAt(left);
                //左移窗口
                left++;
                //进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d)) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        //返回最小覆盖子串
        return len == Integer.MIN_VALUE ? "": s.substring(start, start + len);
    }

    /**
     * 判断 s 中是否存在 t 的排列
     * LeetCode 567 题，Permutation in String，难度 Medium：
     * @param t
     * @param s
     * @return
     */
    public boolean checkInclusion(String t, String s) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c: t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            //进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) valid++;
            }

            //收缩窗口
            while (right - left >= t.length()) {
                //在这里判断是否找到了合法的子串
                if (valid == need.size()) {
                    return true;
                }
                char d = s.charAt(left);
                left++;
                //进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d)) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        //未找到符合条件的子串
        return false;
    }

    /**
     * 找所有字母异位词
     * 这是 LeetCode 第 438 题，Find All Anagrams in a String，难度 Medium：
     * //给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * // 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * // 说明：
     * // 字母异位词指字母相同，但排列不同的字符串。
     * // 不考虑答案输出的顺序。
     *
     * // 示例 1:
     * //输入:
     * //s: "cbaebabacd" p: "abc"
     * //输出:
     * //[0, 6]
     * //解释:
     * //起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * //起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     * //
     * // 示例 2:
     * //输入:
     * //s: "abab" p: "ab"
     * //输出:
     * //[0, 1, 2]
     * //解释:
     * //起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * //起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * //起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     * @param s
     * @param t
     * @return
     */
    public List<Integer> findAnagrams(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c: t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0, valid = 0;
        List<Integer> res = new ArrayList<>(); //记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            //进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) valid++;
            }

            // 判断左侧窗口是否要收缩
            while (right - left >= t.length()) {
                //当窗口符合条件时, 把超始索引加入res
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                //进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d)) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    /**
     * 最长无重复子串
     * 这是 LeetCode 第 3 题，Longest Substring Without Repeating Characters，难度 Medium：
     *
     * //输入: s = "abcabcbb"
     * //输出: 3
     * //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * //输入: s = "bbbbb"
     * //输出: 1
     * //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * //输入: s = "pwwkew"
     * //输出: 3
     * //解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
     * //     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0; //记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            //进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);
            //判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left ++;
                //进行窗口内数据的一系列更新
                window.put(d, window.get(d) - 1);
            }
            //在这里更新答案
            res = Math.max(res, right - left);
        }

        return res;
    }

    public static void main(String[] args) {
        LC双指针 instance = new LC双指针();
        int[] nums = new int[] {2,7,11,15};
        Zlog.log(Arrays.toString(instance.twoSum(nums, 9)));
        Zlog.log(Arrays.toString(instance.twoSum(nums, 18)));

        Zlog.log("=======================");
        Zlog.log(instance.minWindow("ADOBECODEBANC", "ABC"));
        Zlog.log(""+instance.checkInclusion("ab", "eidbaooo"));
        Zlog.log(""+instance.findAnagrams("cbaebabacd", "abc"));
        Zlog.log(""+instance.lengthOfLongestSubstring("abcabcbb"));
        Zlog.log(""+instance.lengthOfLongestSubstring("bbbbb"));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
