package com.zp.demo.concurrent.thread;

import java.util.concurrent.locks.LockSupport;

public class FromA1toZ26 {
    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        t1 = new Thread(() -> {
            for (char c: aI) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c: aC) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
