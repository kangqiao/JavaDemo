package com.zp.demo.concurrent.thread;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    // 普通引用
    private static Person person;

    // 原子性引用
    private static AtomicReference<Person> aRperson;

    public static void main(String[] args) throws InterruptedException {
        //normal();

        atomic();
    }

    private static void atomic() throws InterruptedException {
        person = new Person("Tom", 18);
        aRperson = new AtomicReference<Person>(person);

        System.out.println("Atomic Person is " + aRperson.get().toString());

        Thread t1 = new Thread(new AtomicTask1());
        Thread t2 = new Thread(new AtomicTask2());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Now Atomic Person is " + aRperson.get().toString());
    }

    static class AtomicTask1 implements Runnable {
        public void run() {
            aRperson.getAndSet(new Person("Tom1", aRperson.get().getAge() + 1));

            System.out.println("Thread1 Atomic References "
                    + aRperson.get().toString());
        }
    }

    static class AtomicTask2 implements Runnable {
        public void run() {
            aRperson.getAndSet(new Person("Tom2", aRperson.get().getAge() + 2));

            System.out.println("Thread2 Atomic References "
                    + aRperson.get().toString());
        }
    }

    private static void normal() throws InterruptedException {
        person = new Person("Tom", 18);

        System.out.println("Person is " + person.toString());

        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Now Person is " + person.toString());
    }

    static class Task1 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 1);
            person.setName("Tom1");

            System.out.println("Thread1 Values "
                    + person.toString());
        }
    }

    static class Task2 implements Runnable {
        public void run() {
            person.setAge(person.getAge() + 2);
            person.setName("Tom2");

            System.out.println("Thread2 Values "
                    + person.toString());
        }
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return "[name: " + this.name + ", age: " + this.age + "]";
        }
    }
}
