package com.zp.demo.concurrent.thread;

import java.util.concurrent.CountDownLatch;

public class FromA1toZ26WithSynchronized {

    public static void main(String[] args) {
        final Object o = new Object();
        final CountDownLatch latch = new CountDownLatch(1);

        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c: aI) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t1").start();

        new Thread(() -> {

            synchronized (o) {
                for (char c: aC) {
                    System.out.println(c);
                    latch.countDown();
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
}
