package com.zp.demo.algorithm;

public class 最长公共前缀 {

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight", ""};

        String result = new 最长公共前缀().longestCommonPrefix(strs);
        System.out.println(result);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int count  = strs.length;
        int length0 = strs[0].length();
        for (int i = 0; i < length0; i++) {
            char c  = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
