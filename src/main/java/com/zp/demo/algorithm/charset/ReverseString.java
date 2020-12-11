package com.zp.demo.algorithm.charset;

public class ReverseString {

    public String reverseString(String str) {
        if (null == str || str.length() == 0) return null;

        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char tmp = chars[i]; chars[i] = chars[j]; chars[j] = tmp;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(new ReverseString().reverseString(str));
    }
}
