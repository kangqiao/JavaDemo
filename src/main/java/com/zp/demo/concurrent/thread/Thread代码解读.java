package com.zp.demo.concurrent.thread;

/*
 * Copyright (c) 1994, 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.LockSupport;


/*
    在某个线程中创建一个新线程，新线程的优先级和创建新线程的线程一致，如果创建线程是守护线程的话，则新线程
    是守护线程。

    当Java虚拟机启动时，通常有一个非守护线程（即main线程，调用指定类的main方法），Java虚拟机会继续执行这些线程
    直到发生如下情形之一：
        · 调用了类Runtime的exit()方法，并且安全管理器允许发生退出操作。
        · 所有非守护线程均已死亡，可以是通过执行完run方法后返回，也可以是执行run方法时抛出了异常。

    创建新的执行线程的两种方法：
        · 声明一个Thread类的子类，子类中重写Thread类的run方法。
        · 声明一个实现Runnable接口的类，类中实现run方法。

    每个线程都有一个名字以供识别。线程名字可以相同。如果创建线程时未指定名字，会分配一个新名字。

    除非特殊说明，否则将null参数传递给Thread类的构造器或者方法中会导致NullPointerException。
 */
//public
//class Thread代码解读 implements Runnable {
//    /* Make sure registerNatives is the first thing <clinit> does. */
//    private static native void registerNatives();
//    static {
//        registerNatives();
//    }
//
//    private volatile String name; //线程名称
//    private int            priority; //线程优先级
//    private Thread         threadQ;
//    private long           eetop;
//
//    /* Whether or not to single_step this thread. */
//    private boolean     single_step;
//
//    /* Whether or not the thread is a daemon thread. */
//    private boolean     daemon = false; //是否守护线程标识，初始false
//
//    /* JVM state */
//    private boolean     stillborn = false;
//
//    /* What will be run. */
//    private Runnable target; //线程执行部分
//
//    /* The group of this thread */
//    private ThreadGroup group; //线程的线程组
//
//    /* The context ClassLoader for this thread */
//    private ClassLoader contextClassLoader; //线程的上下文类加载器
//
//    /* The inherited AccessControlContext of this thread */
//    private AccessControlContext inheritedAccessControlContext;
//
//    /* For autonumbering anonymous threads. */
//    private static int threadInitNumber; //静态域，用于分配线程名称
//    private static synchronized int nextThreadNum() {
//        return threadInitNumber++;
//    }
//
//    ThreadLocal.ThreadLocalMap threadLocals = null;
//
//    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
//
//    private long stackSize;
//
//    /*
//     * JVM-private state that persists after native thread termination.
//     */
//    private long nativeParkEventPointer;
//
//
//    private long tid; //线程ID
//
//    private static long threadSeqNumber; //静态域，用于分配线程ID
//
//    private volatile int threadStatus = 0; //线程状态，初始值表示新创建还未启动
//
//    //静态同步方法，用于返回分配给线程的ID
//    private static synchronized long nextThreadID() {
//        return ++threadSeqNumber;
//    }
//
//    volatile Object parkBlocker;
//
//    private volatile Interruptible blocker;
//    private final Object blockerLock = new Object(); //锁对象
//
//    void blockedOn(Interruptible b) {
//        synchronized (blockerLock) {
//            blocker = b;
//        }
//    }
//
//    public final static int MIN_PRIORITY = 1; //最小线程优先级
//
//    public final static int NORM_PRIORITY = 5; //正常线程优先级
//
//    public final static int MAX_PRIORITY = 10; //最大线程优先级
//
//    //返回当前的执行线程对象
//    public static native Thread currentThread();
//
//    //静态方法，导致当前线程处于让步状态，表示当前线程愿意让出CPU资源使得其他线程执行
//    public static native void yield();
//
//    /*
//        静态方法，使当前执行的线程休眠给定的毫秒数，该线程不会放弃所获取的所有锁
//        如果某个线程正在sleep的过程中被别的线程打断，则该线程将抛出InterruptedException异常，并且该线程的中断位会被清除
//     */
//    public static native void sleep(long millis) throws InterruptedException;
//
//    public static void sleep(long millis, int nanos)
//            throws InterruptedException {
//        if (millis < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//
//        if (nanos < 0 || nanos > 999999) {
//            throw new IllegalArgumentException(
//                    "nanosecond timeout value out of range");
//        }
//
//        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
//            millis++;
//        }
//
//        sleep(millis);
//    }
//
//    private void init(ThreadGroup g, Runnable target, String name,
//                      long stackSize) {
//        init(g, target, name, stackSize, null, true);
//    }
//
//    private void init(ThreadGroup g, Runnable target, String name,
//                      long stackSize, AccessControlContext acc,
//                      boolean inheritThreadLocals) {
//        if (name == null) {
//            throw new NullPointerException("name cannot be null");
//        }
//
//        this.name = name;
//
//        Thread parent = currentThread(); //将创建新线程的线程作为父线程
//        SecurityManager security = System.getSecurityManager();
//        if (g == null) {
//            /* Determine if it's an applet or not */
//
//            /* If there is a security manager, ask the security manager
//               what to do. */
//            if (security != null) {
//                g = security.getThreadGroup();
//            }
//
//            /* If the security doesn't have a strong opinion of the matter
//               use the parent thread group. */
//            if (g == null) {
//                g = parent.getThreadGroup(); //和父线程同一个线程组
//            }
//        }
//
//        /* checkAccess regardless of whether or not threadgroup is
//           explicitly passed in. */
//        g.checkAccess();
//
//        /*
//         * Do we have the required permissions?
//         */
//        if (security != null) {
//            if (isCCLOverridden(getClass())) {
//                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
//            }
//        }
//
//        g.addUnstarted();
//
//        this.group = g;
//        this.daemon = parent.isDaemon(); //新线程默认与父线程的守护属性保持一致
//        this.priority = parent.getPriority(); //新线程默认与父线程的优先级保持一致
//        if (security == null || isCCLOverridden(parent.getClass()))
//            this.contextClassLoader = parent.getContextClassLoader();
//        else
//            this.contextClassLoader = parent.contextClassLoader;
//        this.inheritedAccessControlContext =
//                acc != null ? acc : AccessController.getContext();
//        this.target = target;
//        setPriority(priority);
//        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
//            this.inheritableThreadLocals =
//                    ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
//        /* Stash the specified stack size in case the VM cares */
//        this.stackSize = stackSize;
//
//        /* Set thread ID */
//        tid = nextThreadID(); //设置线程ID
//    }
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
//
//    //可以发现：线程名称是采用 "Thread-" + 数字 的方式
//    public Thread() {
//        init(null, null, "Thread-" + nextThreadNum(), 0);
//    }
//
//    public Thread(Runnable target) {
//        init(null, target, "Thread-" + nextThreadNum(), 0);
//    }
//
//    Thread(Runnable target, AccessControlContext acc) {
//        init(null, target, "Thread-" + nextThreadNum(), 0, acc, false);
//    }
//
//    public Thread(ThreadGroup group, Runnable target) {
//        init(group, target, "Thread-" + nextThreadNum(), 0);
//    }
//
//    public Thread(String name) {
//        init(null, null, name, 0);
//    }
//
//    public Thread(ThreadGroup group, String name) {
//        init(group, null, name, 0);
//    }
//
//    public Thread(Runnable target, String name) {
//        init(null, target, name, 0);
//    }
//
//    public Thread(ThreadGroup group, Runnable target, String name) {
//        init(group, target, name, 0);
//    }
//
//    public Thread(ThreadGroup group, Runnable target, String name,
//                  long stackSize) {
//        init(group, target, name, stackSize);
//    }
//
//    public synchronized void start() {
//
//        if (threadStatus != 0)
//            throw new IllegalThreadStateException();
//
//        group.add(this);
//
//        boolean started = false;
//        try {
//            start0();
//            started = true;
//        } finally {
//            try {
//                if (!started) {
//                    group.threadStartFailed(this);
//                }
//            } catch (Throwable ignore) {
//                /* do nothing. If start0 threw a Throwable then
//                  it will be passed up the call stack */
//            }
//        }
//    }
//
//    private native void start0();
//
//    @Override
//    public void run() {
//        if (target != null) {
//            target.run();
//        }
//    }
//
//    private void exit() {
//        if (group != null) {
//            group.threadTerminated(this);
//            group = null;
//        }
//        /* Aggressively null out all reference fields: see bug 4006245 */
//        target = null;
//        /* Speed the release of some of these resources */
//        threadLocals = null;
//        inheritableThreadLocals = null;
//        inheritedAccessControlContext = null;
//        blocker = null;
//        uncaughtExceptionHandler = null;
//    }
//
//    @Deprecated
//    public final void stop() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            checkAccess();
//            if (this != Thread.currentThread()) {
//                security.checkPermission(SecurityConstants.STOP_THREAD_PERMISSION);
//            }
//        }
//        // A zero status value corresponds to "NEW", it can't change to
//        // not-NEW because we hold the lock.
//        if (threadStatus != 0) {
//            resume(); // Wake up thread if it was suspended; no-op otherwise
//        }
//
//        // The VM can handle all thread states
//        stop0(new ThreadDeath());
//    }
//
//    @Deprecated
//    public final synchronized void stop(Throwable obj) {
//        throw new UnsupportedOperationException();
//    }
//
//    /*
//        中断此线程（即中断状态被置为true），除非该线程正在中断自己，否则总是允许的。
//
//        如果该线程因为调用了Object类的wait()方法或者Thread类的join()、sleep()方法处于阻塞状态，则该线程
//        的中断状态会被清除并且产生InterruptedException.
//     */
//    public void interrupt() {
//        if (this != Thread.currentThread())
//            checkAccess();
//
//        synchronized (blockerLock) {
//            Interruptible b = blocker;
//            if (b != null) {
//                interrupt0();           // Just to set the interrupt flag
//                b.interrupt(this);
//                return;
//            }
//        }
//        interrupt0();
//        interrupt0();
//    }
//
//    /*
//        这是静态方法，检测当前线程是否处于中断状态，同时线程的中断状态会被该方法清除（即重置为false）。
//     */
//    public static boolean interrupted() {
//        return currentThread().isInterrupted(true);
//    }
//
//    /*
//        这是实例方法，检测该线程（调用该方法的线程实例）是否处于中断状态，调用该方法不会改变线程的中断状态。
//     */
//    public boolean isInterrupted() {
//        return isInterrupted(false);
//    }
//
//    private native boolean isInterrupted(boolean ClearInterrupted);
//
//    @Deprecated
//    public void destroy() {
//        throw new NoSuchMethodError();
//    }
//
//    //检测该线程是否处于活动状态，活动状态是指该线程被启动（调用start()方法）了并且没有死亡
//    public final native boolean isAlive();
//
//    @Deprecated
//    public final void suspend() {
//        checkAccess();
//        suspend0();
//    }
//
//    @Deprecated
//    public final void resume() {
//        checkAccess();
//        resume0();
//    }
//
//    //设置线程的优先级
//    public final void setPriority(int newPriority) {
//        ThreadGroup g;
//        checkAccess();
//        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {//参数范围检验
//            throw new IllegalArgumentException();
//        }
//        if((g = getThreadGroup()) != null) {
//            if (newPriority > g.getMaxPriority()) {
//                newPriority = g.getMaxPriority();
//            }
//            setPriority0(priority = newPriority);
//        }
//    }
//
//    public final int getPriority() {
//        return priority;
//    }
//
//    public final synchronized void setName(String name) {
//        checkAccess();
//        if (name == null) {
//            throw new NullPointerException("name cannot be null");
//        }
//
//        this.name = name;
//        if (threadStatus != 0) {
//            setNativeName(name);
//        }
//    }
//
//    public final String getName() {
//        return name;
//    }
//
//    public final ThreadGroup getThreadGroup() {
//        return group;
//    }
//
//    public static int activeCount() {
//        return currentThread().getThreadGroup().activeCount();
//    }
//
//    public static int enumerate(Thread tarray[]) {
//        return currentThread().getThreadGroup().enumerate(tarray);
//    }
//
//    @Deprecated
//    public native int countStackFrames();
//
//    public final synchronized void join(long millis)
//            throws InterruptedException {
//        long base = System.currentTimeMillis();
//        long now = 0;
//
//        if (millis < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//
//        if (millis == 0) {
//            while (isAlive()) {
//                wait(0);
//            }
//        } else {
//            while (isAlive()) {
//                long delay = millis - now;
//                if (delay <= 0) {
//                    break;
//                }
//                wait(delay);
//                now = System.currentTimeMillis() - base;
//            }
//        }
//    }
//
//    public final synchronized void join(long millis, int nanos)
//            throws InterruptedException {
//
//        if (millis < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//
//        if (nanos < 0 || nanos > 999999) {
//            throw new IllegalArgumentException(
//                    "nanosecond timeout value out of range");
//        }
//
//        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
//            millis++;
//        }
//
//        join(millis);
//    }
//
//    /*
//        等待指定的线程终止。
//        这里不少初学者都会有个疑惑：到底是谁等待谁终止？
//        回答：假如在A线程中调用了B线程的join()方法（即B.join()），则表示A线程此时进入等待状态，
//              会等待B线程终止才会继续执行。
//     */
//    public final void join() throws InterruptedException {
//        join(0);
//    }
//
//    public static void dumpStack() {
//        new Exception("Stack trace").printStackTrace();
//    }
//
//    /*
//        设置此线程是否为守护线程，true表示守护线程，false表示用户进程。这个方法必须在调用该线程的start()
//        方法之前调用，否则会抛出java.lang.IllegalThreadStateException
//
//        当所有运行的线程都是守护线程时，Java虚拟机将会退出。
//     */
//    public final void setDaemon(boolean on) {
//        checkAccess();
//        if (isAlive()) {
//            throw new IllegalThreadStateException();
//        }
//        daemon = on;
//    }
//
//    //检测该线程是否是守护线程
//    public final boolean isDaemon() {
//        return daemon;
//    }
//
//    public final void checkAccess() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkAccess(this);
//        }
//    }
//
//    public String toString() {
//        ThreadGroup group = getThreadGroup();
//        if (group != null) {
//            return "Thread[" + getName() + "," + getPriority() + "," +
//                    group.getName() + "]";
//        } else {
//            return "Thread[" + getName() + "," + getPriority() + "," +
//                    "" + "]";
//        }
//    }
//
//    @CallerSensitive
//    public ClassLoader getContextClassLoader() {
//        if (contextClassLoader == null)
//            return null;
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            ClassLoader.checkClassLoaderPermission(contextClassLoader,
//                    Reflection.getCallerClass());
//        }
//        return contextClassLoader;
//    }
//
//    public void setContextClassLoader(ClassLoader cl) {
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new RuntimePermission("setContextClassLoader"));
//        }
//        contextClassLoader = cl;
//    }
//
//    public static native boolean holdsLock(Object obj);
//
//    private static final StackTraceElement[] EMPTY_STACK_TRACE
//            = new StackTraceElement[0];
//
//    public StackTraceElement[] getStackTrace() {
//        if (this != Thread.currentThread()) {
//            // check for getStackTrace permission
//            SecurityManager security = System.getSecurityManager();
//            if (security != null) {
//                security.checkPermission(
//                        SecurityConstants.GET_STACK_TRACE_PERMISSION);
//            }
//            // optimization so we do not call into the vm for threads that
//            // have not yet started or have terminated
//            if (!isAlive()) {
//                return EMPTY_STACK_TRACE;
//            }
//            StackTraceElement[][] stackTraceArray = dumpThreads(new Thread[] {this});
//            StackTraceElement[] stackTrace = stackTraceArray[0];
//            // a thread that was alive during the previous isAlive call may have
//            // since terminated, therefore not having a stacktrace.
//            if (stackTrace == null) {
//                stackTrace = EMPTY_STACK_TRACE;
//            }
//            return stackTrace;
//        } else {
//            // Don't need JVM help for current thread
//            return (new Exception()).getStackTrace();
//        }
//    }
//
//    public static Map<Thread, StackTraceElement[]> getAllStackTraces() {
//        // check for getStackTrace permission
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkPermission(
//                    SecurityConstants.GET_STACK_TRACE_PERMISSION);
//            security.checkPermission(
//                    SecurityConstants.MODIFY_THREADGROUP_PERMISSION);
//        }
//
//        // Get a snapshot of the list of all threads
//        Thread[] threads = getThreads();
//        StackTraceElement[][] traces = dumpThreads(threads);
//        Map<Thread, StackTraceElement[]> m = new HashMap<>(threads.length);
//        for (int i = 0; i < threads.length; i++) {
//            StackTraceElement[] stackTrace = traces[i];
//            if (stackTrace != null) {
//                m.put(threads[i], stackTrace);
//            }
//            // else terminated so we don't put it in the map
//        }
//        return m;
//    }
//
//
//    private static final RuntimePermission SUBCLASS_IMPLEMENTATION_PERMISSION =
//            new RuntimePermission("enableContextClassLoaderOverride");
//
//    private static class Caches {
//        /** cache of subclass security audit results */
//        static final ConcurrentMap<WeakClassKey,Boolean> subclassAudits =
//                new ConcurrentHashMap<>();
//
//        /** queue for WeakReferences to audited subclasses */
//        static final ReferenceQueue<Class<?>> subclassAuditsQueue =
//                new ReferenceQueue<>();
//    }
//
//    private static boolean isCCLOverridden(Class<?> cl) {
//        if (cl == Thread.class)
//            return false;
//
//        processQueue(Caches.subclassAuditsQueue, Caches.subclassAudits);
//        WeakClassKey key = new WeakClassKey(cl, Caches.subclassAuditsQueue);
//        Boolean result = Caches.subclassAudits.get(key);
//        if (result == null) {
//            result = Boolean.valueOf(auditSubclass(cl));
//            Caches.subclassAudits.putIfAbsent(key, result);
//        }
//
//        return result.booleanValue();
//    }
//
//    private static boolean auditSubclass(final Class<?> subcl) {
//        Boolean result = AccessController.doPrivileged(
//                new PrivilegedAction<Boolean>() {
//                    public Boolean run() {
//                        for (Class<?> cl = subcl;
//                             cl != Thread.class;
//                             cl = cl.getSuperclass())
//                        {
//                            try {
//                                cl.getDeclaredMethod("getContextClassLoader", new Class<?>[0]);
//                                return Boolean.TRUE;
//                            } catch (NoSuchMethodException ex) {
//                            }
//                            try {
//                                Class<?>[] params = {ClassLoader.class};
//                                cl.getDeclaredMethod("setContextClassLoader", params);
//                                return Boolean.TRUE;
//                            } catch (NoSuchMethodException ex) {
//                            }
//                        }
//                        return Boolean.FALSE;
//                    }
//                }
//        );
//        return result.booleanValue();
//    }
//
//    private native static StackTraceElement[][] dumpThreads(Thread[] threads);
//    private native static Thread[] getThreads();
//
//    public long getId() {
//        return tid;
//    }
//
//    /*
//        线程的六种状态：
//            NEW           新创建
//            RUNNABLE      可运行
//            BLOCKED       阻塞
//            WAITING       等待
//            TIMED_WAITING 计时等待
//            TERMINATED    终止
//     */
//    public enum State {
//        //实例化一个线程后还没有调用其start()方法，处于新创建状态
//        NEW,
//
//        //处于可运行状态的线程可能正在运行也可能未运行（等待操作系统调度）
//        RUNNABLE,
//
//        //尝试获取锁但是未成功，等待锁
//        BLOCKED,
//
//        /*
//            一个线程调用了下面三个方法会进入等待状态：
//                Object.wait()
//                Thread.join()
//                LockSupport.park()
//         */
//        WAITING,
//
//        /*
//            一个线程调用了下面的方法会进入超时等待状态：
//                Thread.sleep()
//                带参数的Object.wait()
//                带参数的Thread.join()
//                LockSupport.parkNanos()
//                LockSupport.parkUntil()
//         */
//        TIMED_WAITING,
//
//        //run方法执行结束返回后或者run方法抛出未捕获的异常会导致线程终止
//        TERMINATED;
//    }
//
//    /*
//        返回该线程的状态
//     */
//    public State getState() {
//        // get current thread state
//        return sun.misc.VM.toThreadState(threadStatus);
//    }
//
//    // Added in JSR-166
//
//    @FunctionalInterface
//    public interface UncaughtExceptionHandler {
//        void uncaughtException(Thread t, Throwable e);
//    }
//
//    // null unless explicitly set
//    private volatile UncaughtExceptionHandler uncaughtExceptionHandler;
//
//    // null unless explicitly set
//    private static volatile UncaughtExceptionHandler defaultUncaughtExceptionHandler;
//
//    public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(
//                    new RuntimePermission("setDefaultUncaughtExceptionHandler")
//            );
//        }
//
//        defaultUncaughtExceptionHandler = eh;
//    }
//
//    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler(){
//        return defaultUncaughtExceptionHandler;
//    }
//
//    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
//        return uncaughtExceptionHandler != null ?
//                uncaughtExceptionHandler : group;
//    }
//
//    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
//        checkAccess();
//        uncaughtExceptionHandler = eh;
//    }
//
//    private void dispatchUncaughtException(Throwable e) {
//        getUncaughtExceptionHandler().uncaughtException(this, e);
//    }
//
//    static void processQueue(ReferenceQueue<Class<?>> queue,
//                             ConcurrentMap<? extends
//                                     WeakReference<Class<?>>, ?> map)
//    {
//        Reference<? extends Class<?>> ref;
//        while((ref = queue.poll()) != null) {
//            map.remove(ref);
//        }
//    }
//
//    static class WeakClassKey extends WeakReference<Class<?>> {
//        private final int hash;
//
//        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
//            super(cl, refQueue);
//            hash = System.identityHashCode(cl);
//        }
//
//        @Override
//        public int hashCode() {
//            return hash;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (obj == this)
//                return true;
//
//            if (obj instanceof WeakClassKey) {
//                Object referent = get();
//                return (referent != null) &&
//                        (referent == ((WeakClassKey) obj).get());
//            } else {
//                return false;
//            }
//        }
//    }
//
//    @sun.misc.Contended("tlr")
//    long threadLocalRandomSeed;
//
//    @sun.misc.Contended("tlr")
//    int threadLocalRandomProbe;
//
//    @sun.misc.Contended("tlr")
//    int threadLocalRandomSecondarySeed;
//
//    /* Some private helper methods */
//    private native void setPriority0(int newPriority);
//    private native void stop0(Object o);
//    private native void suspend0();
//    private native void resume0();
//    private native void interrupt0();
//    private native void setNativeName(String name);
//}
