package com.zp.demo.algorithm.charset;

public class 两个数字字符串相加 {
    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "457";
        System.out.println(str1 + "+ \n" + str2 + "\n" + twoStringAdd(str1, str2));

        str1 = "12356789";
        str2 = "4571234567890";
        System.out.println(str1 + " + \n" + str2 + "\n" + twoStringAdd(str1, str2));
    }

    public static String twoStringAdd(String str1, String str2) {
       if (str1 == null || str1.length() == 0) return str2;
       if (str2 == null || str2.length() == 0) return str1;

       StringBuilder sb = new StringBuilder();
       int i = str1.length() - 1, j = str2.length() - 1;

       int k = 0;
       while (i >= 0 || j >= 0) {
           if (i >= 0) {
               char num = str1.charAt(i--);
               k += num - '0';
           }
           if (j >= 0) {
               char num = str2.charAt(j--);
               k += num - '0';
           }

           if (k >= 10) {
               sb.append(k%10);
               k = k/10;
           } else {
               sb.append(k);
               k = 0;
           }
       }
       return sb.reverse().toString();
    }
}
