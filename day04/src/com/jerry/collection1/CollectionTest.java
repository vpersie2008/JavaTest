package com.jerry.collection1;

/*
 * 集合存储都优点，解决数组存储的弊端。
 * */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 * ArrayList 是有序的，可重复的数组
 * 1. contains 用于判断，集合中是否包含某个元素
 * 2. containsAll 用于判断，集合中是否包含某些元素，即某些元素是否为该集合的子集
 * 3. 如果判断对象是否在该集合中，默认使用contains判断，比较的是地址值，需要比较内容，则需要重写 equals方法
 * 4. 使用Arrays.asList() 方法，将一个数组转换为list
 * */
public class CollectionTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 32));

        //System.out.println(coll.contains(123));

        //重写了equals方法，则比较的是内容，所以是true，如果没有重写equals，则比地址，就是false
        System.out.println(coll.contains(new Person("Jerry", 34)));

        //新的list是否在collection中存在，即是否为子集
        Collection coll2 = Arrays.asList(123, 456);
        System.out.println(coll.containsAll(coll2));
    }

    //移除remove测试
    @Test
    public void test2() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);

        //移除某个特定元素，该元素必须在里面才能移除
        col1.remove(4);
        System.out.println(col1);

        //removeAll移除另一个list和该集合的交集
        Collection col2 = Arrays.asList(1, 1234);
        col1.removeAll(col2);
        System.out.println(col1);
    }

    //交集retainAll测试
    @Test
    public void test3() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);


        Collection col2 = new ArrayList();
        col2.add(1);
        col2.add(2);

        //求交集
        col1.retainAll(col2);
        System.out.println(col1);
    }

    //hashCode() 返回当前对象的哈希值
    @Test
    public void test4() {
        Collection col1 = new ArrayList();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);

        //返回对象的哈希值
        System.out.println(col1.hashCode());

        //集合到数组的转换
        Object[] arr1 = col1.toArray();
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }

        //数组转为集合
        List<String> list1 = Arrays.asList(new String[]{"1", "2", "test"});
        System.out.println(list1);

        //装整型的list
        List list2 = Arrays.asList(1, 2, 3, 5, 8);
        System.out.println(list2);
    }
}
