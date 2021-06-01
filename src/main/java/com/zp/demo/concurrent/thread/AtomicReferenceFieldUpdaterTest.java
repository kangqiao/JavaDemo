package com.zp.demo.concurrent.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.UnaryOperator;

/**
 * AtomicReference内部持有真实的对象
 * AtomicReferenceFieldUpdater不持有对象, 使用CAS操作引用对象的属性.
 * ARF对象占内存; 一个对象在32位JVM中占12B+4B=16Byte; 64位JVM中占16B+8B=24B;
 * ARFU不占内存; 一般做类的静态成员; 操作所有类的实例对象.
 * ARFU应用多: 1. 在BufferedInputStream中有应用,
 * 2. 在Kotlin的lazy应用lazy(LazyThreadSafetyMode.PUBLICATION){}中使用ARFU来初始化第一次; 而正常lazy则使用synchronized(this)
 */
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicReferenceValueHolder.test();
        System.out.println("==========================");
        SimpleValueHolder.test();
    }
}

class SimpleValueHolder{
    public static AtomicReferenceFieldUpdater<SimpleValueHolder, String> valueUpdate = AtomicReferenceFieldUpdater.newUpdater(SimpleValueHolder.class, String.class, "value");
    volatile String value = "Hello";

    public static void test() {
        SimpleValueHolder holder = new SimpleValueHolder();
        SimpleValueHolder.valueUpdate.compareAndSet(holder, "Hello", "World");
        System.out.println(SimpleValueHolder.valueUpdate.get(holder));
        String value = SimpleValueHolder.valueUpdate.getAndUpdate(holder, new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return "HelloWorld";
            }
        });
        System.out.println(value);
    }
}


class AtomicReferenceValueHolder {
    AtomicReference<String> atomicValue = new AtomicReference<>("HelloAtomic");

    public static void test () {
        AtomicReferenceValueHolder holder = new AtomicReferenceValueHolder();
        //如果当前atomicValue的值是Hello, 则更新为world
        holder.atomicValue.compareAndSet("Hello", "world");
        System.out.println(holder.atomicValue.get());
        //先获取再更新, 其中UnaryOperator的apply中可以获取当旧值, 返回要更新的值, 最后compareAndSet比较,相等, 再更新.
        String value = holder.atomicValue.getAndUpdate(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return "HelloWorld";
            }
        });
        System.out.println(value);
    }
}
