package com.zp.demo.instrument;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * JPDA（Java Platform Debugger Architecture）。
 * 如果JVM启动时开启了JPDA，那么类是允许被重新加载的。在这种情况下，已被加载的旧版本类信息可以被卸载，然后重新加载新版本的类。
 */
public class TestAgent {

    public static void agentmain(String args, Instrumentation inst) {
        //指定我们自己定义的Transformer, 在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);

        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
            System.out.println("agent load failed!");
        }
    }
}
