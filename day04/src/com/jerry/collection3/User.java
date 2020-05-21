package com.jerry.collection3;

import java.util.Objects;

public class User implements Comparable {
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
