package com.zp.demo.basic.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericReturnTypeTest {

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("--------GenericReturnTypeTest----------");
        ParameterizedType methodType = (ParameterizedType) SubClass.class.getMethod("getValue").getGenericReturnType();
        ParameterizedType[] annotatedReturnType = (ParameterizedType[]) SubClass.class.getMethod("getValue").getGenericParameterTypes();
        ParameterizedType[] genericExceptionTypes = (ParameterizedType[]) SubClass.class.getMethod("getValue").getGenericExceptionTypes();

        System.out.println(methodType);
        for (Type type: methodType.getActualTypeArguments()) {
            System.out.println(type);
            for (Type innerType: ((ParameterizedType) type).getActualTypeArguments()) {
                System.out.println(innerType);
            }
        }

        //Type collectionType = new TypeToken<Collection<Integer>>() {}.getType();
    }
}

class SuperClass<T>{

}

class SubClass extends SuperClass<String> {
    public List<Map<String, Integer>> getValue() {
        return null;
    }
}
