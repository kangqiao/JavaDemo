package com.zp.demo.algorithm.charset;

public class MyAtoi {

    public int myatoi(String str) {

        // 1. Empty String
        if (null == str || str.length() == 0) return 0;

        int index = 0, sign = 1, total = 0;

        // 2. Remove Spaces
        while (str.charAt(index) == ' ') {
            index++;
        }

        // 3. Handle sign
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        // 4. Convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total
                    && Integer.MAX_VALUE % 10 < digit) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + digit;
            index++;
        }

        return sign * total;
    }

    public static void main(String[] args) {
        MyAtoi atoi = new MyAtoi();
        String s1 = "   -1234";
        String s2 = "   -12341234566667";
        String s3 = "1a2345";
        String s4 = String.valueOf(Integer.MAX_VALUE - 1);
        System.out.println(atoi.myatoi(s1));
        System.out.println(atoi.myatoi(s2));
        System.out.println(atoi.myatoi(s3));
        System.out.println(atoi.myatoi(s4));

    }
}
