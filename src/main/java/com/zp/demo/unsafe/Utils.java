package com.zp.demo.unsafe;

import com.zp.demo.Zlog;
//import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 */
public class Utils {

  /*  public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            Zlog.log(e.getMessage());
            return null;
        }
    }*/
}
