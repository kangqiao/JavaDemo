package com.zp.demo.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author muyou
 * @date 2020/10/14 10:44
 * Semapore类中的
 *
 *
 * Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。
 *      使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 *
 * Semaphore的构造方法Semaphore(int permits) 接受一个整型的数字，表示可用的许可证数量.
 *      Semaphore(10)表示允许10个线程获取许可证，也就是最大并发数是10。
 * Semaphore的主要方法摘要：
 * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。 还可以用tryAcquire()方法尝试获取许可证。
 *     void acquire(int permits)参数作用以及动态添加permits许可数量
 * 　　void release():释放一个许可，将其返回给信号量。
 * 　　void release(int permits): 释放permits许可，将其返回给信号量。
 * 　　int availablePermits():返回此信号量中当前可用的许可数。
 *    int getQueueLength()：返回正在等待获取许可证的线程数。
 * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取许可证。
 *    void reducePermits(int reduction) ：减少reduction个许可证。是个protected方法。
 *    Collection getQueuedThreads() ：返回所有等待获取许可证的线程集合。是个protected方法。
 *
 * 方法availablePermits()是返回当前可用的许可数量
 * 方法drainPermits()是返回立即可用的所有许可数量，并将可用许可数置为0
 * 方法getQueueLength()作用是取得等待许可的线程个数
 * 方法hasQueuedThreads()作用是判断有没有线程正在等待这个许可
 * 方法tryAcquire()作用是尝试获取1个许可，如果获取不到就返回false，常与if等条件判断语句连用
 * 方法tryAcquire(int primits)作用是尝试获取primits个许可，如果获取不到就返回false
 * 方法tryAcquire(int primits,long timeout,TimeUnit time)作用是在指定时间内获取primits个许可，如果获取失败返回false
 * 原文链接：https://blog.csdn.net/u013851082/article/details/70208246
 */
public class Semaphore信号量 {
    /**
     * 有参方法acquire(int permits)作用是每调用一次就使用permits个许可
     *如果多次调用release()\release(int permits)方法，还可以动态增加许可的个数
     */
    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(10);
            //semaphore.availablePermits()方法作用是显示当前可用的许可数量
            System.out.println("当前可用许可数量为：" + semaphore.availablePermits());
            semaphore.acquire(2);
            semaphore.acquire();
            semaphore.acquire(7);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
            semaphore.release(2);
            semaphore.release();
            semaphore.release(7);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
            semaphore.release(5);
            System.out.println("当前可用许可数量为："+semaphore.availablePermits());
//            当前可用许可数量为：10
//            当前可用许可数量为：0
//            当前可用许可数量为：10
//            当前可用许可数量为：15
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}