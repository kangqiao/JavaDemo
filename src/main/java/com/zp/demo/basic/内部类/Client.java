package com.zp.demo.basic.内部类;

import java.lang.reflect.Field;

public class Client {
    public String nameC = "AAAAddd";
    public void run() {
        final Object obj = new Object();
        OuterClass.InnerClass innerClass = new OuterClass().new InnerClass() {
            @Override
            void test() {
                System.out.println("this:"+this);
                System.out.println("Client.this:"+Client.this);
                System.out.println("getOuterClass:"+this.getOuterClass());
                System.out.println("obj:"+obj);
                Field[] fields = this.getClass().getDeclaredFields();
                try {
                    System.out.println("---:"+fields.length);
                    for (Field f: fields) {
                        Object fobj = f.get(this);
                        System.out.println(fobj);
                        if (fobj instanceof Client) {
                            System.out.println("---:");
                            System.out.println(((Client)fobj).nameC);
                        }

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println("innerClass:"+innerClass);
        innerClass.test();
        OuterClass.InnerInterface innerInterface = new OuterClass.InnerInterface() {
            @Override
            public void test() {
            }
        };
    }

    public static void main(String[] args) {
        new Client().run();
    }
}
//class Client$1 {
//    /**
//     * @param client 自己的外部类实例
//     * @param outerClass 非静态父类的外部类实例
//     * @param obj 捕获外部变量
//     */
//    public Client$1(Client client, OuterClass outerClass, Object obj) {
//        ...
//    }
//}
//class Client$2 {
//    public Client$2(Client client) {
//        ...
//    }
//}

class OuterClass {
    public String name = "AAA";
    public abstract class InnerClass {
        abstract void test();

        public OuterClass getOuterClass() {
            return OuterClass.this;
        }
    }
    interface InnerInterface {
        void test();
    }
}