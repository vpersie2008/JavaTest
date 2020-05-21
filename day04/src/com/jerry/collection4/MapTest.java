package com.jerry.collection4;

import org.junit.Test;

import java.util.*;

/*
 * 一、Map 的实现结构：
 *   |----Map:双列数据，存储以key-value 对的数据。
 *       |----HashMap：作为Map的主要实现类，线程不安全的，可以存储null 的key 和value
 *           |----LinkedHashMap：保证在遍历map元素时，可以按照添加的顺序实现遍历
 *               原因：在原有的HashMap 底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 *                   对于频繁的遍历操作，此类执行效率高于HashMap
 *       |----TreeMap： 保证按照添加的key-value 对进行排序，实现排序遍历，此时考虑Key 的自然排序，底层使用红黑树来存储。
 *
 *       |----Hashtable：作为古老实现类：线程安全的，效率低，不能存储null值得key-value
 *           |----Properties：常用来处理配置文件。key 和value 都是String 类型的。
 *
 *      HashMap 底层：数组 + 链表 （JDK7 及之前版本）
 *                   数组+链表+红黑树（JDK8 及以上版本）
 *
 *      面试题：
 *      1.HashMap 底层实现原理？
 *      2.HashMap 和 Hashtable 的异同？
 *
 * */


public class MapTest {

    //map常用方法
    @Test
    public void test1() {
        Map map = new HashMap();

        //添加
        map.put("AA", 1);
        map.put("BB", 2);
        map.put(3, 3);

        //修改
        map.put("BB", 100);

        System.out.println(map);

        //添加另一个map到当前map
        Map map1 = new HashMap();
        map1.put("CC", 123);
        map1.put("DD", 123);
        map.putAll(map1);

        System.out.println(map);

        //移除指定key 的数据
        Object obj = map.remove(3);
        System.out.println(map);

        //clear() 清空,只是清空里面的数据
        map.clear();
        System.out.println(map);


    }

    //map 查询相关
    @Test
    public void test2() {
        Map map = new HashMap();
        map.put("AA", 1);
        map.put("BB", 2);
        map.put(3, 333);
        map.put(4, 333);

        //1.查找操作
        System.out.println(map.get(3));

        //2.判断是否存在某个key
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        //3.判断是否存在某个value
        isExist = map.containsValue(333);
        System.out.println(isExist);

        //4.判断是否为空，其实只是判断内容是否没有
        System.out.println(map.isEmpty());

        //5.清空
        map.clear();
        System.out.println(map);
    }

    //map 遍历操作
    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 1);
        map.put("BB", 2);
        map.put(3, 333);
        map.put(4, 333);

        //1.遍历所有的key集，keySet();
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("****************************************");
        //2.遍历所有的value
        Collection values = map.values();
        for (Object obj : values) {
            System.out.println(obj);
        }

        System.out.println("****************************************");
        //3.遍历所有的key-value,方式一：
        Set entrySet = map.entrySet();
        Iterator iEntrySet = entrySet.iterator();
        while (iEntrySet.hasNext()) {
            Object obj = iEntrySet.next();
            Map.Entry entry = (Map.Entry) obj;

            System.out.println("Key is : " + entry.getKey() + " Value is : " + entry.getValue());
        }

        System.out.println("****************************************");

        //4.遍历所有的key-value,方式二：
        Set keySet1 = map.keySet();
        Iterator iterator1 = keySet1.iterator();

        while (iterator1.hasNext()) {
            Object key = iterator1.next();
            Object value = map.get(key);
            System.out.println("Key is : " + key + " Value is : " + value);
        }
    }

}
