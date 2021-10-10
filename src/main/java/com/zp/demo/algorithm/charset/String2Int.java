package com.zp.demo.algorithm.charset;

public class String2Int {

    public static void main(String[] args) throws Exception {
        String s1 = "-092376";
        String s2 = "-a92376";
        String s3 = "92376";
        String s4 = "923a76";
        System.out.println("s1转换前的str：" + s1);
        System.out.println("s1转换后的int：" + stringtoint(s1));
        System.out.println("s2转换前的str：" + s2);
        System.out.println("s2转换后的int：" + stringtoint(s2));
        System.out.println("s3转换前的str：" + s3);
        System.out.println("s3转换后的int：" + stringtoint(s3));
        System.out.println("s4转换前的str：" + s4);
        System.out.println("s4转换后的int：" + stringtoint(s4));

    }

    /**
     * 不用内置转换函数把String转换成int -1 代表转换失败
     *
     * @param strnum
     * @return
     */
    public static int stringtoint(String strnum) {
        int number = 0;
        boolean isNavigate = false;

        if (strnum == null || strnum.length() == 0) {
            return -1;
        }

        for (int i=0; i<strnum.length(); i++) {
            char num = strnum.charAt(i);
            if (i==0 && num == '-') {
                isNavigate = true;
                continue;
            }
            if (num < '0' || num > '9') {
                return -1;
            }
            number = number * 10 + (num - '0');
        }

        return isNavigate ? -number: number;
    }
}
