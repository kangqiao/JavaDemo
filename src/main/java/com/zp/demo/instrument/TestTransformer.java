package com.zp.demo.instrument;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);

        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.zp.demo.instrument.Base");
            CtMethod cm = cc.getDeclaredMethod("process");
            cm.insertBefore("{System.out.println(\"start\");}");
            cm.insertAfter("{System.out.println(\"end\");}");
            return cc.toBytecode();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
