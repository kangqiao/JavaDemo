package com.zp.demo;

import java.nio.charset.Charset;
import java.util.function.BinaryOperator;

public class Main {

    String a = "a";

    public static void main(String[] args) {
//        System.out.println(~0);
//        Main main = new Main();
//
//        run(main);
//        main.a = null;
//        main = null;
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//
//        Zlog.log("" + calcSpeed(123456, 233));
//        Zlog.log("" + calcSpeed(123, 2));
//        Zlog.log("" + calcSpeed(-11, 234));
//        Zlog.log("" + calcSpeed(-11234567, 234));
//        Zlog.log("" + calcSpeed(0, 0));
//        Zlog.log("" + calcSpeed(98765, 0));
//        Zlog.log("" + calcSpeed(987654321, 234467238));
//        Zlog.log("" + calcSpeed(Long.MAX_VALUE, Long.MIN_VALUE));
        //byteCount=25159, rctTimeNanos=169795677

        //Zlog.log("" + (123 / 100f));

//        Zlog.log("" + calcSpeed(25159, 516 - 378));
//        Zlog.log("" + calcSpeed(25159, 1169795677));
//
//        Zlog.log(Runnable.class.getCanonicalName());

        String bstr = new String("asgdhfjg我".getBytes(), Charset.defaultCharset());
        Zlog.log("" + bstr.length());
        Zlog.log("" + bstr.getBytes().length);
        int l = String.valueOf('c').getBytes().length;
        Zlog.log(""+l);
        int l2 = String.valueOf('我').getBytes().length;
        Zlog.log(""+l2);
    }

    /**
     * 计算数据传输速度
     * @param byteCount
     * @param timeMillis
     * @return
     */
    private static final double factor = 1024d / 1000d;
    public static double calcSpeed(long byteCount, long timeMillis) {
        if (timeMillis <= 0 || byteCount <= 0) return 0;
        double ret = byteCount / timeMillis / factor;
        return Math.round(ret * 100) / 100d;
    }

    private static void run(Main main) {
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                System.out.println(main.a.equals("a"));
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
