package com.jerry.collection2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * 迭代器测试
 * */
public class IteratorTest {

    @Test
    public void test1() {

        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(new Person("Jerry", 22));
        col1.add(false);

        //整成迭代器
        Iterator iterator = col1.iterator();

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());

        //如果超出next，则报异常 java.util.NoSuchElementException
        //System.out.println(iterator.next());

    }

    //迭代器遍历
    @Test
    public void test2() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(new Person("Tom", 33));
        col1.add(false);

        //整成迭代器
        Iterator iterator = col1.iterator();

        //必须用hasNext判断下一个节点是否有值
        while (iterator.hasNext()) {
            //每次都会把next 对象返回
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    //迭代器移除测试
    @Test
    public void test3() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(new Person("Tom", 33));
        col1.add("Tom");
        col1.add(false);

        //整成迭代器
        Iterator iterator = col1.iterator();

        while (iterator.hasNext()) {
            //每次都会把next 对象返回
            Object obj = iterator.next();
            if ("Tom".equals(obj)) {
                //移除当前对象
                iterator.remove();
            }
        }


        //移除后遍历,注意这里必须重新获取迭代器,
        //为什么要重新获取迭代器呢？因为上面迭代器已经跳出了，他已经没有hasNext 了，所以再遍历上一个iterator 是遍历不到的
        Iterator iterator2 = col1.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

    //使用增强for循环遍历
    @Test
    public void test4() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(new Person("Tom", 33));
        col1.add("Tom");
        col1.add(false);

        //这里遍历只是将list中的item依次枚举
        for (Object obj : col1) {
            System.out.println(obj);
        }
    }

    //使用增强for循环遍历
    @Test
    public void test5() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);

        //这里遍历只是将list中的item依次枚举,所以这里不会改变原有的list,即原有的元素不变
        for (Object item : col1) {
            item = "Test";
        }

        //打印，还是原有的list
        System.out.println(col1);
    }

}
