package com.zp.demo.javassist;

import javassist.*;

public class Test {

    public int f(int i) {
        return i + 1;
    }

    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.get("com.zp.demo.javassist.Test");

        try {
            cc.getDeclaredMethod("g");
            System.out.println("g() is already defined in sample.Test.");
        } catch (NotFoundException e) {
            //e.printStackTrace();
            /* getDeclaredMethod() throws an exception if g()
             * is not defined in sample.Test.
             */
            CtMethod fMethod = cc.getDeclaredMethod("f");
            CtMethod gMethod = CtNewMethod.copy(fMethod, "g", cc, null);
            cc.addMethod(gMethod);
            cc.writeFile("build/classes/java/main/"); //update the class file
            System.out.println("g() was added.");
        }


    }
}
