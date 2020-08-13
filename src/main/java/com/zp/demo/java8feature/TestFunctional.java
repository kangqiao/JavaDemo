package com.zp.demo.java8feature;

import com.zp.demo.Zlog;

import java.util.Objects;
import java.util.function.*;

public class TestFunctional {

    public static void main(String[] args) {
        //testBinaryOperator();

        //testBiPredicate();

        //testBiConsumer();

        //testBooleanSupplier();

        testUnaryOperator();
    }

    //接受一个参数, 返回同参数类型的返回值.
    private static void testUnaryOperator() {
        LongUnaryOperator ms2s = (l) -> l / 1000;
        LongUnaryOperator s2m = (l) -> l / 60;
        LongUnaryOperator m2h = (l) -> l / 60;
        LongUnaryOperator h2d = (l) -> l / 24;


        long nowTimeMillis = 7 * 24 * 60 * 60 * 1000 + 1234561234;

        long  d = ms2s.compose(s2m).compose(m2h).compose(h2d).applyAsLong(nowTimeMillis);

        Zlog.log(String.valueOf(d));
    }

    //提供一个返回Boolean类型的函数.
    private static void testBooleanSupplier() {
        BooleanSupplier bs = () -> true;
        System.out.println(bs.getAsBoolean());

        int x = 0, y= 1;
        bs = () -> x > y;
        System.out.println(bs.getAsBoolean());
    }

    // 接受两个参数, 没有返回值
    private static void testBiConsumer() {
        BiConsumer<Integer, String> consumer = (a, b) ->  System.out.println(a + b);
        consumer.accept(5, " Chapters");

        BiConsumer<Integer, Integer> addition = (a, b) -> Zlog.log(String.valueOf(a + b));
        BiConsumer<Integer, Integer> subtraction = (a, b) -> Zlog.log(String.valueOf(a - b));
        addition.andThen(subtraction).accept(10, 6);
    }

    //接受两个参数, 返回boolean值.
    private static void testBiPredicate() {
        BiPredicate<String, String> equalContent = (a, b) -> a.equals(b);
        BiPredicate<String, String> equalLength = (a, b) -> a.length() == b.length();

        boolean ret = equalContent.or(equalLength).test("abc", "def");
        Zlog.log("BiPredicate:" + ret);
    }

    // 接受两个参数, 有返回值.  BinaryOperator implements BiFunction
    private static void testBinaryOperator() {
        BinaryOperator bMin = BinaryOperator.<Integer>minBy((a, b) -> a > b ? 1: a==b ? 0: -1);



        Zlog.log("BinaryOperator:" + bMin.apply(10, 20));
        Zlog.log("BinaryOperator:" + bMin.apply(10, 10));
        Zlog.log("BinaryOperator:" + bMin.apply(20, 10));
    }
}
