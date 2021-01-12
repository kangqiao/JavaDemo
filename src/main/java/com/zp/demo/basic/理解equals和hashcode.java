package com.zp.demo.basic;

import java.util.HashSet;

/**
 * https://www.cnblogs.com/skywang12345/p/3324958.html
 * 第1部分 equals() 的作用
 * equals() 的作用是 用来判断两个对象是否相等。
 * 下面，举例对上面的2种情况进行说明。
 * 1.  “没有覆盖equals()方法”的情况
 * 2. "覆盖equals()方法"的情况
 *
 * 第2部分 equals() 与 == 的区别是什么？
 * == : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不试同一个对象。
 * equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况(前面第1部分已详细介绍过)：
 *                  情况1，类没有覆盖equals()方法。则通过equals()比较该类的两个对象时，等价于通过“==”比较这两个对象。
 *                  情况2，类覆盖了equals()方法。一般，我们都覆盖equals()方法来两个对象的内容相等；若它们的内容相等，则返回true(即，认为这两个对象相等)。
 *
 * 第3部分 hashCode() 的作用
 * hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
 * hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。
 *         虽然，每个Java类都包含hashCode() 函数。但是，仅仅当创建并某个“类的散列表”(关于“散列表”见下面说明)时，该类的hashCode() 才有用(作用是：确定该类的每一个对象在散列表中的位置；其它情况下(例如，创建类的单个对象，或者创建类的对象数组等等)，类的hashCode() 没有作用。
 *        上面的散列表，指的是：Java集合中本质是散列表的类，如HashMap，Hashtable，HashSet。
 *        也就是说：hashCode() 在散列表中才有用，在其它情况下没用。在散列表中hashCode() 的作用是获取对象的散列码，进而确定该对象在散列表中的位置。
 *
 * 第4部分 hashCode() 和 equals() 的关系
 * 接下面，我们讨论另外一个话题。网上很多文章将 hashCode() 和 equals 关联起来，有的讲的不透彻，有误导读者的嫌疑。在这里，我自己梳理了一下 “hashCode() 和 equals()的关系”。
 * 我们以“类的用途”来将“hashCode() 和 equals()的关系”分2种情况来说明。
 *
 * 1. 第一种 不会创建“类对应的散列表”
 *         这里所说的“不会创建类对应的散列表”是说：我们不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。例如，不会创建该类的HashSet集合。
 *         在这种情况下，该类的“hashCode() 和 equals() ”没有半毛钱关系的！
 *         这种情况下，equals() 用来比较该类的两个对象是否相等。而hashCode() 则根本没有任何作用，所以，不用理会hashCode()。
 *
 * 下面，我们通过示例查看类的两个对象相等 以及 不等时hashCode()的取值。
 *
 * 2. 第二种 会创建“类对应的散列表”
 *         这里所说的“会创建类对应的散列表”是说：我们会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。例如，会创建该类的HashSet集合。
 *         在这种情况下，该类的“hashCode() 和 equals() ”是有关系的：
 *         1)、如果两个对象相等，那么它们的hashCode()值一定相同。
 *               这里的相等是指，通过equals()比较两个对象时返回true。
 *         2)、如果两个对象hashCode()相等，它们并不一定相等。
 *                因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。然而哈希值相等，并不一定能得出键值对相等。补充说一句：“两个不同的键值对，哈希值相等”，这就是哈希冲突。
 *         此外，在这种情况下。若要判断两个对象是否相等，除了要覆盖equals()之外，也要覆盖hashCode()函数。否则，equals()无效。
 * 例如，创建Person类的HashSet集合，必须同时覆盖Person类的equals() 和 hashCode()方法。
 *         如果单单只是覆盖equals()方法。我们会发现，equals()方法没有达到我们想要的效果。
 */
public class 理解equals和hashcode {

    public static void main(String[] args) {
        // 新建Person对象，
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        Person p4 = new Person("EEE", 100);

        // 新建HashSet对象
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);

        // 比较p1 和 p2， 并打印它们的hashCode()
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        // 比较p1 和 p4， 并打印它们的hashCode()
        System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
        // 打印set
        System.out.printf("set:%s\n", set);


        /**
         * 运行结果：
         *
         *  p1.equals(p2) : true; p1(68545) p2(68545)
         * p1.equals(p4) : false; p1(68545) p4(68545)
         * set:[aaa - 200, eee - 100]
         * 结果分析：
         *         这下，equals()生效了，HashSet中没有重复元素。
         *         比较p1和p2，我们发现：它们的hashCode()相等，通过equals()比较它们也返回true。所以，p1和p2被视为相等。
         *         比较p1和p4，我们发现：虽然它们的hashCode()相等；但是，通过equals()比较它们返回false。所以，p1和p4被视为不相等。
         */
    }

    /**
     * @desc Person类。
     */
    private static class Person {
        int age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " - " +age;
        }

        /**
         * @desc重写hashCode
         */
        @Override
        public int hashCode(){
            int nameHash =  name.toUpperCase().hashCode();
            return nameHash ^ age;
        }

        /**
         * @desc 覆盖equals方法
         */
        @Override
        public boolean equals(Object obj){
            if(obj == null){
                return false;
            }

            //如果是同一个对象返回true，反之返回false
            if(this == obj){
                return true;
            }

            //判断是否类型相同
            if(this.getClass() != obj.getClass()){
                return false;
            }

            Person person = (Person)obj;
            return name.equals(person.name) && age==person.age;
        }
    }
}
