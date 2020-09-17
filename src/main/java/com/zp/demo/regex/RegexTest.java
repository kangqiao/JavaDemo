package com.zp.demo.regex;

import com.zp.demo.Zlog;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
//        matchPathRule("/-/x/pro/v2/beta/common/currencies", ".*");
//        matchPathRule("/-/x/pro/v2/beta/common/currencies", "^/-/x/.*");
//        matchPathRule("/-/x/pro/v2/beta/common/currencies", ".*v2/.*");
//        matchPathRule("/-/x/pro/v2/beta/common/currencies", ".*v2/.*/currencies.*");
//        matchPathRule("/-/x/pro/v2/beta/common/currencies", ".*common/currencies.*");
//
//        int timeout = 5000;
//        Object[] argss = new Object[]{timeout};
//        Zlog.log((argss[0] instanceof Integer) + " zhaopan");
//
//        int a = 12345 / 2;
//        Zlog.log(a+"");

        testA();
        testB();

        AtomicInteger a = new AtomicInteger(0);
        a.getAndAdd(0);
    }

    private static void matchPathRule(String path, String regex) {
        Pattern p = Pattern.compile(regex);

        Zlog.log( "\"" + path + "\".matches(\"" + regex + "\") -> " + p.matcher(path).matches());


    }


    private static void testA() {
        Pattern p = Pattern.compile(".*");
        Map<String, Pattern> mmm = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            mmm.put("www.baidu.com" + i, p);
        }

        long start = System.nanoTime();
        for (long i = 0; i < 1; i++) {
            String path = "/-/x/pro/v2/beta/common/currencies" + i;
            p = mmm.get("www.baidu.com" + (i % 10));
            if (p != null) {
                p.matcher(path).matches();
            }
        }
        long end = System.nanoTime();

        Zlog.log("testA = " + (end - start));
    }

    private static void testB() {
        String regex = ".*";
        Pattern p = Pattern.compile(".*");
        Map<String, Pattern> mmm = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            mmm.put("www.baidu.com" + i, p);
        }

        long start = System.nanoTime();
        for (long i = 0; i < 1; i++) {
            String path = "/-/x/pro/v2/beta/common/currencies" + i;
            p = mmm.get("www.baidu.com" + (i % 10));
            if (".*".equals(regex)) {

            } else if (p != null) {
                p.matcher(path).matches();
            }
        }
        long end = System.nanoTime();

        Zlog.log("testB = " + (end - start));
    }
}
