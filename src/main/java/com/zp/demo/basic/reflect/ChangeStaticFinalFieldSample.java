package com.zp.demo.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ChangeStaticFinalFieldSample {

    static void changeStaticFinal(Field field, Object newValue) throws Exception {
        field.setAccessible(true); // 如果field为private,则需要使用该方法使其可被访问

        Field modifersField = Field.class.getDeclaredField("modifiers");
        modifersField.setAccessible(true);
        // 把指定的field中的final修饰符去掉
        modifersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue); // 为指定field设置新值
    }

    public static void main(String[] args) throws Exception {
        Sample.print();
        
        Field canChangeField = Sample.class.getDeclaredField("CAN_CHANGE");
        Field cannotChangeField = Sample.class.getDeclaredField("CANNOT_CHANGE");
        changeStaticFinal(canChangeField, "zhao");
        changeStaticFinal(cannotChangeField, 3);
        
        Sample.print();
    }
}

final class Sample {
    private static final String CAN_CHANGE = new String("abc1"); // 未内联优化
    private static final int CANNOT_CHANGE = 1; // 内联优化


    public static void print() {
        System.out.println("CAN_CHANGE = " + CAN_CHANGE);
        System.out.println("CANNOT_CHANGE = " + CANNOT_CHANGE);
        System.out.println("------------------------");
    }
}