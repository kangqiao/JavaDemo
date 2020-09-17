package com.zp.demo.jol;

import org.openjdk.jol.info.ClassLayout;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        test();
    }

    private static void test() {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        int[] aa = new int[0];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
    }

    public static class A {}

    public static class B {
        private long s;
    }

    public static class C {
        private int a;
        private long s;
    }

    static int[] aa = new int[0];
}
