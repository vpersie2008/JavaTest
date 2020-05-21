package com.jerry.collection4;

import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;

/*
 *
 *     向TreeMap 中添加key-value，要求key 必须是由同一个类创建的对象。
 * */

public class TreeMapTest {

    //一、自然排序
    @Test
    public void test1() {
        TreeMap map = new TreeMap();
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 24);
        User u3 = new User("Kim", 33);
        User u4 = new User("Tony", 38);
        User u5 = new User("Jack", 22);

        map.put(u1, 99);
        map.put(u2, 98);
        map.put(u3, 97);
        map.put(u4, 95);
        map.put(u5, 94);

        System.out.println(map);
    }

    //二、定制排序,按照年龄从小到大排序
    @Test
    public void test2() {

        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }

                throw  new RuntimeException("输入类型不匹配！");
            }
        });
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 24);
        User u3 = new User("Kim", 33);
        User u4 = new User("Tony", 38);
        User u5 = new User("Jack", 22);

        map.put(u1, 99);
        map.put(u2, 98);
        map.put(u3, 97);
        map.put(u4, 95);
        map.put(u5, 94);

        //打印结果
        System.out.println(map);
    }

}


class User implements Comparable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //按照姓名，从小到大排序,TreeSet 是根据这个方法来判断是否重复才需要添加的
    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return this.name.compareTo(user.name);
        } else {
            throw new RuntimeException("输入的类型不匹配");
        }
    }
}
