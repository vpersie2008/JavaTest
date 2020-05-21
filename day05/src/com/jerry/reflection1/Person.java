package com.jerry.reflection1;

public class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("Person constructor");
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show() {
        System.out.println("This is a person");
    }

    private void showNation(String nation) {
        System.out.println("My nation is : " + nation);
    }

    private static void showDesc(String desc) {
        System.out.println("My description is: " + desc );
    }
}
