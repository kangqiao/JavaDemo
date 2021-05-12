package com.zp.demo.bytecode.javassist;

import javassist.*;

import java.io.IOException;

public class JavassistTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException {
        //Base b = new Base();
        ClassPool cp = ClassPool.getDefault();

        CtClass cc = cp.get("com.zp.demo.bytecode.javassist.Base");
        CtMethod cm = cc.getDeclaredMethod("process");
        cm.insertBefore("{System.out.println(\"start\");}");
        cm.insertAfter("{System.out.println(\"end\");}");
        Class c = cc.toClass();
        cc.writeFile("build/classes/java/main");
        Base h = (Base)c.newInstance();
        h.process();
    }
}
