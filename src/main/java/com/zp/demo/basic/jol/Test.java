package com.zp.demo.basic.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;


/**
 * https://www.jianshu.com/p/6d62c3ee48d0
 * JOL简介
 * JOL的全称是Java Object Layout。是一个用来分析JVM中Object布局的小工具。包括Object在内存中的占用情况，实例对象的引用情况等等。
 * JOL可以在代码中使用，也可以独立的以命令行中运行。命令行的我这里就不具体介绍了，今天主要讲解怎么在代码中使用JOL。
 *
 * JOL全称为Java Object Layout，是分析JVM中对象布局的工具，该工具大量使用了Unsafe、JVMTI来解码布局情况，所以分析结果是比较精准的，接下来我们就具体操作下。
 *
 * java对象的组成部分：
 *
 * 1.对象头
 *      在jvm虚拟机中每一个java对象都有一个对象头，对象头中包含标记字段以及对象指针，标记字段用来储存hash码、gc信息以及锁信息，而指针则指向改对象的类。在64位jvm虚拟机中这两部分都是64位的，所以也就是需要128位大小（16 bytes）。
 *      注意：64位虚拟机中在堆内存小于32GB的情况下，UseCompressedOops是默认开启的，该参数表示开启指针压缩，会将原来64位的指针压缩为32位。
 * 2.实例数据
 *      类中所有的实例字段数据。
 * 3.内存填充部分（alignment）
 *      该部分作用是用来保证java对象在虚拟机中占内存大小为8N bytes。
 * 4.数组长度
 *      这个是数组对象才特有的。
 *
 * java的基础数据类型所占内存情况如下表格：
 * boolean	byte	short	char	int	long	float	double
 * 1 bytes	1 bytes	2 bytes	2 bytes	4 bytes	8 bytes	4 bytes	8 bytes
 */
public class Test {
    public static class A {}
    public static class B {
        private long s;
    }
    public static class C {
        private int a;
        private long s;
    }
    static int[] aa = new int[0];

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        System.out.println(VM.current().details());
        /**
         * # Running 64-bit HotSpot VM.
         * # Using compressed oop with 3-bit shift.
         * # Using compressed klass with 3-bit shift.
         * # WARNING | Compressed references base/shifts are guessed by the experiment!
         * # WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
         * # WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
         * # Objects are 8 bytes aligned.
         * # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
         * # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
         */
        System.out.println("---------------");

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /**
         * java.lang.Object object internals:
         * OFF  SZ   TYPE DESCRIPTION               VALUE
         *   0   8        (object header: mark)     0x0000000000000005 (biasable; age: 0)
         *   8   4        (object header: class)    0xf80001e5
         *  12   4        (object alignment gap)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            /**
             * java.lang.Object object internals:
             * OFF  SZ   TYPE DESCRIPTION               VALUE
             *   0   8        (object header: mark)     0x00007fbc7400e005 (biased: 0x0000001fef1d0038; epoch: 0; age: 0)
             *   8   4        (object header: class)    0xf80001e5
             *  12   4        (object alignment gap)
             * Instance size: 16 bytes
             * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
             */
        }

        System.out.println("---------------");
        test();
    }

    private static void test() {
        A a = new A();
        /**
         * A对象：A对象就是一个空对象，所以在内存中占据大小就是对象头的大小等于16 bytes，由于开启指针压缩则对象头占据大小为12 bytes，
         * 但是12 bytes字节不是8的倍数所以需要进行内存对齐，最后加上了4 bytes的空白字节，最终该对象占据16 bytes大小。
         */
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        /**
         * com.zp.demo.basic.jol.Test$A object internals:
         * OFF  SZ   TYPE DESCRIPTION               VALUE
         *   0   8        (object header: mark)     0x0000000000000005 (biasable; age: 0)
         *   8   4        (object header: class)    0xf800d352
         *  12   4        (object alignment gap)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        B b = new B();
        /**
         * B对象：B对象包含一个long基本数据类型，所以大小等于 12 bytes+8 bytes=20 bytes，
         * 20 bytes不是8N所以需要加上4 bytes的填充字节，最终该对象占据24 bytes。
         */
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        /**
         * com.zp.demo.basic.jol.Test$B object internals:
         * OFF  SZ   TYPE DESCRIPTION               VALUE
         *   0   8        (object header: mark)     0x0000000000000005 (biasable; age: 0)
         *   8   4        (object header: class)    0xf800d3ce
         *  12   4        (alignment/padding gap)
         *  16   8   long B.s                       0
         * Instance size: 24 bytes
         * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
         */
        C c = new C();
        /**
         * C对象：C对象包含一个long、一个int基本数据类型，所以大小等于 12 bytes+8 bytes+4 bytes=24 bytes，正好8N，不需要内存填充，
         * 最终该对象占据24 bytes。
         */
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        /**
         * com.zp.demo.basic.jol.Test$C object internals:
         * OFF  SZ   TYPE DESCRIPTION               VALUE
         *   0   8        (object header: mark)     0x0000000000000005 (biasable; age: 0)
         *   8   4        (object header: class)    0xf800d390
         *  12   4    int C.a                       0
         *  16   8   long C.s                       0
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        int[] aa = new int[0];
        /**
         * D对象：D对象是一个数组对象，因为数组对象会多一个数组长度存储部分，所以大小等于12 bytes+4 bytes=16 bytes，
         * 注意这里数组长度我给的是0，如果给的是1大家可以试试看输出结果又是多少呢。
         */
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        /**
         * [I object internals:
         * OFF  SZ   TYPE DESCRIPTION               VALUE
         *   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
         *   8   4        (object header: class)    0xf800016d
         *  12   4        (array length)            0
         *  12   4        (alignment/padding gap)
         *  16   0    int [I.<elements>             N/A
         * Instance size: 16 bytes
         * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
         */
        int[] bb = new int[1];
        System.out.println(ClassLayout.parseInstance(bb).toPrintable());
         /**
          * [I object internals:
          * OFF  SZ   TYPE DESCRIPTION               VALUE
          *   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
          *   8   4        (object header: class)    0xf800016d
          *  12   4        (array length)            1
          *  12   4        (alignment/padding gap)
          *  16   4    int [I.<elements>             N/A
          *  20   4        (object alignment gap)
          * Instance size: 24 bytes
          * Space losses: 4 bytes internal + 4 bytes external = 8 bytes total
          */
    }
}
