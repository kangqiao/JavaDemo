package com.zp.demo.poet;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class HowToJavaPoetDemo {
    public static void main(String[] args) {
        // `JavaFile` 代表 Java 文件
        FieldSpec field = FieldSpec.builder(int.class, "mField", Modifier.PRIVATE)
                .build();
        JavaFile javaFile = JavaFile.builder("com.walfud.howtojavapoet",
                // TypeSpec 代表一个类
                TypeSpec.classBuilder("Clazz")
                        // 给类添加一个属性
                        .addField(field)
                        // 给类添加一个方法
                        .addMethod(MethodSpec.methodBuilder("method")
                                .addModifiers(Modifier.PUBLIC)
                                .returns(void.class)
                                .addStatement("System.out.println($N)", field)
                                .build())
                        .build())
                .build();

        System.out.println(javaFile.toString());
    }
}
