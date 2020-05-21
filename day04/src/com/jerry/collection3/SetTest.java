package com.jerry.collection3;
/*
 *一、Set 接口框架：
 *  |---- Collection 接口：单例集合，用来存储一个一个的对象
 *          |----Set 接口：存储无序的、不可重复的数据。
 *              |----HashSet: 作为Set 接口的主要实现类：线程不安全的，可以存储null值。
 *                  |----LinkedHashSet：作为HashSet的子类：遍历时可以按照添加顺序遍历。
 *              |----TreeSet: 可以按照添加对象的指定属性，进行排序。
 *
 * 二、Set: 存储无序的，不可重复的数据
 * 以HashSet 为例：
 * 1.无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值，根据一定算法添加到数组中的某个位置。
 * 2.不可重复性：保证添加的元素按照equals() 判断时，不能返回true，即相同元素只能添加一个。
 *
 * 三、添加元素的过程：以HashSet 为例：
 *   3.1 ：我们向HashSet 中添加元素a，首先调用元素a 所在的hashCode()方法，计算元素a的hash值，
 *       此hash值接着通过某种算法计算出HashSet 底层数组中的存放位置（即：索引位置），判断数组此位置上是否已经有元素：
 *       情况1：如果此位置上没有其他元素，则直接添加成功。
 *       情况2：如果此位置上有其他元素b (或以链表形式存在的多个元素)，则比较a 与 b 元素的hash值，
 *           如果hash值不同，则添加元素a成功。
 *           如果hash值相同，则需要调用元素a所在类的equals方法，
 *               如果equals方法返回true，则添加失败。
 *               情况3：如果equals方法返回false，则元素a添加成功。
 *
 *   3.2 ： 说明：对于情况2和情况3，元素a 与已经存在指定索引位置上的数据以链表形式存储。
 *      jdk7 ：元素a 放到数组中，指向原来元素。
 *      jdk8 : 原来的元素在数组中，指向元素a。
 *      总结来说：七上八下
 *
 *  四、TreeSet
 *  4.1 TreeSet 用于存储同类型的对象，不能存储不同类型的对象，这个Set 用于排序，默认是从小到大的。
 *      如果要自定义排序规则，则需要重写元素对象类中的Comparable 接口
 *      在TreeSet 上判断是否相同去添加到Set 中，是用的Comparable，如果返回true则不会添加进来.
 *
 * */

import org.junit.Test;

import java.util.*;

public class SetTest {

    //HashSet测试
    @Test
    public void test1() {
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        //这块添加了重复的数据，会只添加1份，因为认为重复了。
        set1.add(4);
        set1.add(4);
        //添加了重复的对象，他认为是比地址，所以这块会添加进去
        set1.add(new Person("Jerry", 22));
        set1.add(new Person("Jerry", 22));

        //打印的顺序，并不会一定是添加的顺序，因为它是按hash算法来加到数组中的，存储方式是无序的。
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //LinkedHashSet测试,添加的顺序，和打印的顺序一致，但他存储的时候，还是无序的。
    //它作为HashSet的子类，当添加数据时，还维护了数据的一对双向链表，记录链表添加的顺序。
    //优点：作为频繁的遍历操作，它的效率要高于HashSet
    @Test
    public void test2() {
        Set set2 = new LinkedHashSet();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        //这块添加了重复的数据，会只添加1份，因为认为重复了。
        set2.add(4);
        set2.add(4);
        //添加了重复的对象，他认为是比地址，所以这块会添加进去
        set2.add(new Person("Jerry", 22));
        set2.add(new Person("Jerry", 22));

        //LinkedHashSet，也是添加不重复的数据，但添加顺序和打印顺序一致
        Iterator iterator = set2.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //HashSet ，重写User 类中的equals和hashCode 方法，让其比较方式以内容方式比较
    @Test
    public void test3() {
        Set set3 = new HashSet();
        set3.add(1);
        set3.add(2);
        set3.add(3);
        //这块添加了重复的数据，会只添加1份，因为认为重复了。
        set3.add(4);
        set3.add(4);
        //添加了重复的对象，他认为是比地址，所以这块会添加进去
        set3.add(new User("Jerry", 22));
        set3.add(new User("Jerry", 22));

        //这里打印出来的就只会是一份Jerry，因为我们重写了当前类的equals和hashcode方法，让其以内容方式进行比较。
        Iterator iterator = set3.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //TreeSet 测试：默认从小到大排列
    @Test
    public void test4() {
        Set set4 = new TreeSet();
        set4.add(21);
        set4.add(-2);
        set4.add(33);
        set4.add(22);

        Iterator iterator = set4.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //TreeSet 测试：
    @Test
    public void test5() {
        TreeSet set5 = new TreeSet();
        set5.add(11);
        set5.add(-2);
        set5.add(33);
        set5.add(22);

        Iterator iterator = set5.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //TreeSet 测试：自定义对象排序,按照name进行排序
    @Test
    public void test6() {
        TreeSet set6 = new TreeSet();

        set6.add(new User("Jerry", 11));
        set6.add(new User("Tom", 12));
        set6.add(new User("Harry", 13));
        set6.add(new User("Peter", 14));
        set6.add(new User("Kim", 15));
        //添加重复对象，是添加不进去的，因为这里的comparable只指定了name的判断规则，
        //若想添加进去，则需要指定二级判断规则,即age
        set6.add(new User("Kim", 16));

        Iterator iterator = set6.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //TreeSet 测试：自定义对象排序,按照name 和 age进行排序
    @Test
    public void test7() {

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {

                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());

                } else {
                    throw new RuntimeException("输入类不匹配");
                }
            }
        };

        TreeSet set7 = new TreeSet(comparator);
        set7.add(new User("Jerry", 11));
        set7.add(new User("Tom", 12));
        set7.add(new User("Harry", 13));
        set7.add(new User("Peter", 14));
        set7.add(new User("Kim", 15));

        //这里能添加进去，因为进行了定制比较排序,定制排序中只按照age进行排序
        set7.add(new User("Kim", 16));

        Iterator iterator = set7.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
