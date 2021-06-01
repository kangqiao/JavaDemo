package com.zp.demo.concurrent.thread;

import com.zp.demo.Zlog;

import java.util.concurrent.TimeUnit;

public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        //testJoin();
//        testYield();
//        testInterrupted();
        //testInterruptedNonBlock();
        //testInterruptedBlock();

        testInterruptedWait();
    }

    /**
     * 与sleep方法不同的是wait方法调用完成后，线程将被暂停，
     * 但wait方法将会释放当前持有的监视器锁，直到有线程调用notify/notifyAll方法后方能继续执行，而sleep方法只让线程休眠并不释放锁。
     * 同时notify/notifyAll方法调用后，并不会马上释放监视器锁，而是在相应的synchronized(){}/synchronized方法执行结束后才自动释放锁。
     * @throws InterruptedException
     */
    private static void testInterruptedWait() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                Zlog.dt("AAAA");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Zlog.dt("BBB");
                }
                Zlog.dt("CCC");
            }
        });
        Zlog.dt("000");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
    }

    /**
     * // 中断线程（实例方法）
     * public void Thread.interrupt();
     * // 判断线程是否被中断（实例方法）
     * public boolean Thread.isInterrupted();
     * // 判断是否被中断并清除当前中断状态（静态方法） 中断状态将会被复位（由中断状态改为非中断状态）
     * public static boolean Thread.interrupted();
     * @throws InterruptedException
     */
    private static void testInterrupted() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interruted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
        /**
         * 输出结果:
         * Interruted When Sleep
         * interrupt:false
         *
         *  try {
         *     //判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
         *     while (!Thread.interrupted()) {
         *         TimeUnit.SECONDS.sleep(2);
         *     }
         *     } catch (InterruptedException e) {
         *         // ......
         *     }
         */
    }

    /**
     * 虽然我们调用了interrupt方法，但线程t1并未被中断，因为处于非阻塞状态的线程需要我们手动进行中断检测并结束程序
     */
    public static void testInterruptedNonBlock() throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    System.out.println("未被中断");
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /**
         * 输出结果(无限执行):
         *    未被中断
         *    未被中断
         *    未被中断
         *    ......
         */
    }

    /**
     * 我们在代码中使用了实例方法isInterrupted判断线程是否已被中断，如果被中断将跳出循环以此结束线程，注意非阻塞状态调用interrupt()并不会导致中断状态重置。
     * @throws InterruptedException
     */
    public static void testInterruptedBlock() throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    // 判断当前线程是否被中断
                    if (this.isInterrupted()){
                        System.out.println("线程中断");
                        break;
                    }
                }

                System.out.println("已跳出循环,线程中断!");
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /**
         * 输出结果:
         *   线程中断
         *   已跳出循环,线程中断!
         */
    }

    //yield() 线程让步，暂停当前正在执行的线程，把执行权给其他线程，执行其他的线程
    private static void testYield() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            //synchronized (obj) {
//                try {
//                    Zlog.dt("sleep 2000 111 ");
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Zlog.dt("sleep 2000 111 ");
                while (!Thread.interrupted()) {
                    Thread.yield();
                }
                Thread.yield();
                Zlog.dt("sleep 2000 222 ");
            //}

        });
        Thread t2 = new Thread(() -> {
            t1.interrupt();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Zlog.dt("t2 >>>>>>>");
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

    //join() 线程加入或者线程合并，加入的线程执行结束才会执行其他的线程。
    private static void testJoin() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Zlog.dt("sleep 3000 begin ");
                    Thread.sleep(3000);
                    Zlog.dt("sleep 3000 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Zlog.dt("join begin");
        t1.join();
        Zlog.dt("join after");
    }
}
