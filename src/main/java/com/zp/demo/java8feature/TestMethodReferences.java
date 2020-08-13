package com.zp.demo.java8feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 方法引用通过方法的名字来指向一个方法。
 *
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 *
 * 方法引用使用一对冒号 :: 。
 *
 * 下面，我们在 Car 类中定义了 4 个方法作为例子来区分 Java 中 4 种不同方法的引用。
 */
//https://www.runoob.com/java/java8-method-references.html
public class TestMethodReferences {

    public static void main(String[] args) {

        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach( Car::collide );

        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach( Car::repair );

        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );

        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }

}
@FunctionalInterface
interface Supplier<T> {
    T get();
}

class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static com.zp.demo.java8feature.Car create(final Supplier<com.zp.demo.java8feature.Car> supplier) {
        return supplier.get();
    }

    public static void collide(final com.zp.demo.java8feature.Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final com.zp.demo.java8feature.Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}


