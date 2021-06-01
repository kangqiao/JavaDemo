package com.zp.demo.concurrent;

import com.zp.demo.Zlog;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC顺序输出 {

    public static void main(String[] args) {
        //printWithJoin();
        //printABCTimes();
        //printSemaphore();
        //printWithWaitNotify();
        printWithLockCondition();
    }

    /**
     * 使用Lock/Condition输出times次ABC.
     */
    private static void printWithLockCondition() {
        int times = 3;
        state = 0;
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(() -> {
            print("A", 0, times, lock, conditionA, conditionB);
        }).start();
        new Thread(() -> {
            print("B", 1, times, lock, conditionB, conditionC);
        }).start();
        new Thread(() -> {
            print("C", 2, times, lock, conditionC, conditionA);
        }).start();
    }
    private static void print(String name, int targetState, int times, Lock lock, Condition current, Condition next) {
        for (int i=0; i<times;){
            lock.lock();
            try {
                while (state % 3 != targetState) {
                    current.await();;
                }
                state++;
                i++;
                System.out.print(name);
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 使用wait/notify 输出times次ABC
     */
    private static void printWithWaitNotify() {
        int times = 4;
        state = 0;
        Object objectA = new Object();
        Object objectB = new Object();
        Object objectC = new Object();
        new Thread(() -> {
            print("A", 0, times, objectA, objectB);
        }).start();
        new Thread(() -> {
            print("B", 1, times, objectB, objectC);
        }).start();
        new Thread(() -> {
            print("C", 2, times, objectC, objectA);
        }).start();
    }
    private static void print(String name, int targetState, int times, Object current, Object next) {
        for (int i=0; i<times;) {
            synchronized (current) {
                while (state % 3 != targetState) {
                    try {
                        current.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                state++;
                i++;
                System.out.print(name);
                synchronized (next) {
                    next.notify();
                }
            }
        }
    }

    /**
     * 使用Semaphore 依次输出times次ABC
     *
     * Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。
     *      使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
     *
     * Semaphore的构造方法Semaphore(int permits) 接受一个整型的数字，表示可用的许可证数量.
     *      Semaphore(10)表示允许10个线程获取许可证，也就是最大并发数是10。
     * Semaphore的主要方法摘要：
     * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。 还可以用tryAcquire()方法尝试获取许可证。
     * 　　void release():释放一个许可，将其返回给信号量。
     * 　　int availablePermits():返回此信号量中当前可用的许可数。
     *    int getQueueLength()：返回正在等待获取许可证的线程数。
     * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取许可证。
     *    void reducePermits(int reduction) ：减少reduction个许可证。是个protected方法。
     *    Collection getQueuedThreads() ：返回所有等待获取许可证的线程集合。是个protected方法。
     * 原文链接：https://blog.csdn.net/u013851082/article/details/70208246
     */
    private static void printSemaphore() {
        int times = 5;
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);
        new Thread(() -> {
            print("A", times, semaphoreA, semaphoreB);
        }).start();
        new Thread(() -> {
            print("B", times, semaphoreB, semaphoreC);
        }).start();
        new Thread(() -> {
            print("C", times, semaphoreC, semaphoreA);
        }).start();
    }
    public static void print(String name, int times, Semaphore current, Semaphore next) {
        for (int i = 0; i < times; ++i) {
            try {
                current.acquire(); //请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(name);
            next.release(); //释放许可，许可数加1
        }
    }

    /**
     * 三个线程分别打印A,B,C,要求这三个线程一起运行,打印n次,输出形如“ABCABCABC
     */
    static int state = 0;
    private static void printABCTimes() {
        Lock lock = new ReentrantLock();
        state = 0;
        int times = 5;
        new Thread(() -> {
            print(lock, "A", 0, times);
        }).start();
        new Thread(() -> {
            print(lock, "B", 1, times);
        }).start();
        new Thread(() -> {
            print(lock, "C", 2, times);
        }).start();
    }
    private static void print(Lock lock, String name, int target, int times) {
        for (int i=0; i<times;) {
            lock.lock();
            if (state % 3 == target) {
                state++;
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

    private static void printWithJoin() {
        Thread tA = new Thread(() -> {
            System.out.print("AAA");
        });
        Thread tB = new Thread(() -> {
            try {
                tA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("BBB");
        });
        Thread tC = new Thread(() -> {
            try {
                tB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("CCC");
        });

        tC.start();
        tB.start();
        tA.start();
    }
}
