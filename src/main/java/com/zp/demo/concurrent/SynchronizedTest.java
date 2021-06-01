package com.zp.demo.concurrent;

/**
 * https://blog.csdn.net/qq_35246620/article/details/106311989
 *
 */
public class SynchronizedTest {
    public int i;

    public void syncTask(){
        // 同步代码块
        synchronized (this){
            i++;
        }
    }

    public synchronized void syncTask2(){
        i++;
    }

    public void waitReleaseMonitor() {
        synchronized (this) {
            try {
                this.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * // class version 52.0 (52)
 * // access flags 0x21
 * public class com/zp/demo/concurrent/SynchronizedTest {
 *
 *   // compiled from: SynchronizedTest.java
 *
 *   // access flags 0x1
 *   public I i
 *
 *   // access flags 0x1
 *   public <init>()V
 *    L0
 *     LINENUMBER 7 L0
 *     ALOAD 0
 *     INVOKESPECIAL java/lang/Object.<init> ()V
 *     RETURN
 *    L1
 *     LOCALVARIABLE this Lcom/zp/demo/concurrent/SynchronizedTest; L0 L1 0
 *     MAXSTACK = 1
 *     MAXLOCALS = 1
 *
 *   // access flags 0x1
 *   public syncTask()V
 *     TRYCATCHBLOCK L0 L1 L2 null
 *     TRYCATCHBLOCK L2 L3 L2 null
 *    L4
 *     LINENUMBER 12 L4
 *     ALOAD 0
 *     DUP
 *     ASTORE 1
 *     MONITORENTER  //进入同步方法
 *    L0
 *     LINENUMBER 13 L0
 *     ALOAD 0
 *     DUP
 *     GETFIELD com/zp/demo/concurrent/SynchronizedTest.i : I
 *     ICONST_1
 *     IADD
 *     PUTFIELD com/zp/demo/concurrent/SynchronizedTest.i : I
 *    L5
 *     LINENUMBER 14 L5
 *     ALOAD 1
 *     MONITOREXIT  //退出同步方法
 *    L1
 *     GOTO L6
 *    L2
 *    FRAME FULL [com/zp/demo/concurrent/SynchronizedTest java/lang/Object] [java/lang/Throwable]
 *     ASTORE 2
 *     ALOAD 1
 *     MONITOREXIT  //退出同步方法
 *    L3
 *     ALOAD 2
 *     ATHROW
 *    L6
 *     LINENUMBER 15 L6
 *    FRAME CHOP 1
 *     RETURN
 *    L7
 *     LOCALVARIABLE this Lcom/zp/demo/concurrent/SynchronizedTest; L4 L7 0
 *     MAXSTACK = 3
 *     MAXLOCALS = 3
 * }
 */