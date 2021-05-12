package com.zp.demo.concurrent.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FromA1toZ26LockCon {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition t1 = lock.newCondition();
        Condition t2 = lock.newCondition();
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.println(c);
                    t2.signal();
                    t1.await();
                }
                t2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    t1.signal();
                    t2.await();
                }
                t1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
