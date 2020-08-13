package com.zp.demo.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.LOCAL_VARIABLE})
public @interface MyAnnotation {

    String value();
    String name();
    int age();
    String[] newNames();
}
