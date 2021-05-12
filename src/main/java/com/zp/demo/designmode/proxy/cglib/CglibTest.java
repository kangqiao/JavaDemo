package com.zp.demo.designmode.proxy.cglib;

import com.zp.demo.Zlog;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibTest {

    public static void main(String[] args) {
        // CGLIB动态代理
        // 1. 首先实现一个MethodInterceptor，方法调用会被转发到该类的intercept()方法。
        class MyMethodInterceptor implements MethodInterceptor {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Zlog.log("You said: " + Arrays.toString(args));
                return proxy.invokeSuper(obj, args);
            }
        }
        // 2. 然后在需要使用HelloConcrete的时候，通过CGLIB动态代理获取代理对象。
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());
        /*// etc.
        enhancer.setStrategy(new DefaultGeneratorStrategy() {
            protected byte[] transform(byte[] b) {
                // do something with bytes here
                return new TransformingGenerator(cg,
                        new AddPropertyTransformer(new String[]{ "foo" },
                                new Class[]{ Integer.TYPE }));
            }
        });*/

        HelloConcrete hello = (HelloConcrete) enhancer.create();
        Zlog.log(hello.sayHello("I love you!"));

        Zlog.log("---------------");
        Zlog.log("class=" + hello.getClass());
        Zlog.log("superClass=" + hello.getClass().getSuperclass());
        for (Class clazz : hello.getClass().getInterfaces()) {
            Zlog.log("interfaces:" + clazz);
        }

    }

    public void print() {
        System.out.println("hello world!!!");
    }
}
