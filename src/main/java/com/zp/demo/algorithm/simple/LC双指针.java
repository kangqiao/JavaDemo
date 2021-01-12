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

import java.util.Arrays;
import java.util.HashMap;

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

    public static void main(String[] args) {
        LC双指针 instance = new LC双指针();
        int[] nums = new int[] {2,7,11,15};
        Zlog.log(Arrays.toString(instance.twoSum(nums, 9)));
        Zlog.log(Arrays.toString(instance.twoSum(nums, 18)));

        Zlog.log("=======================");
        Zlog.log(instance.minWindow("ADOBECODEBANC", "ABC"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
