package com.zp.demo;

import java.util.Date;

public class Zlog {

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void dt(String msg) {
        System.out.println(new Date().toString() + "\t" + Thread.currentThread() + "\t" +  msg);
    }
}
