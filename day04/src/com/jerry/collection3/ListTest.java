package com.jerry.collection3;

/*
 * 一、List详解
 *
 * |---- Collection 接口：单列集合，用来存储一个一个的对象
 *       |----List 接口：存储有序的、可重复的数据。 ---> "动态"数组，替换原有的数组
 *           |---- ArrayList：作为List 的主要实现类，1.2有了，执行效率高，因为他是线程不安全的；底层采用 Object[] elementData 来存储
 *           |---- LinkedList：底层采用双向链表存储，对于频繁的插入和删除操作，使用此类比ArrayList 效率要高。
 *           |---- VectorList：作为List的古老实现类，在JDK1.0 就有了，执行效率相对较低，因为他是线程安全的；底层采用 Object[] elementData 来存储
 *
 * 面试题：这三者有什么异同？
 * 相同点：三个类都实现了List 接口，存储数据都是有序的，可重复的数据。
 * 不同点：见上。
 *
 * 二、ArrayList 源码分析
 *  2.1 --- JDK7 当中：
 *  ArrayList list = new  ArrayList();// 底层创建了长度是10的Object[] 数组 elementData
 *  list.add(1);// 相当于elementData[0] = new Integer(1);
 *  ...
 *  list.add(11);//如果此次添加的elementData 数组容量不够，则扩容。
 *  默认情况下，扩容为原来容量的1.5倍，同时需要将原有数组中的数据复制到数组中。
 *  结论：建议在开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity);
 *  2.2 --- JDK8 当中：
 *  ArrayList list = new  ArrayList();// 底层 Object[] elementData 初始为{}，并没有创建带长度的数组
 *  list.add(1);// 第一次调用add操作时，底层才创建了长度为10 的数组，并将元素1添加到elementData 数组中。
 *  。。。
 *  后续添加和扩容与JDK7 无异。
 *  总结：JDK7 中的ArrayList 创建类似于单例模式的饿汉式，而JDK8 当中类似于懒汉式，延迟了数组的创建，节省了内存空间。
 *
 *  对于频繁查询，建议用ArrayList，对于频繁插入，删除，建议用LinkedList
 *
 * 三、LinkedList 源码分析
 *  LinkedList list2 = new LinkedList();// 内部声明了Node 类型的first 和 last属性，默认值为null，
 *  list.add(1);//将元素1封装到Node 中，创建了Node 对象。
 *
 * 四、Vector 源码分析,JDK 7 和 JDK8 中通过Vector() 构造器创建对象时，底层创建了长度为10 的数组，
 * 在扩容方面，默认扩容为数组长度的2倍。
 *
 * */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListTest {
    @Test
    public void test1() {
        List list1 = new ArrayList();
        list1.add(1);

        List list2 = new LinkedList();
        list2.add(2);

        List list3 = new Vector();
        list3.add(3);
    }

    @Test
    public void test2() {
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        //set
        list1.set(1, "aa");
        System.out.println(list1);

        //subList,左闭右开
        List list2 = list1.subList(0, 2);
        System.out.println(list2);
    }

    //面试题：删的是索引，还是元素？
    @Test
    public void test3() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        updateList(list);
        System.out.println(list);
    }

    private static void updateList(List list){
        //删除的是索引
        list.remove(2);
        //删除的是对象
        list.remove(new Integer(4));
    }
}
