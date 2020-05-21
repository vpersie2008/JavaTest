package com.jerry.thread7;
/*
 * 一、懒汉式的线程安全写法
 * */

public class SingletonTest {

    public static void main(String[] args) {

    }

}

//一、性能较低的线程安全懒汉式写法
class Bank1 {
    private Bank1() {
    }

    private static Bank1 instance = null;

    //这种写法，效率较低，因为第一次已经将instance创建出来了，其他线程再去用的时候，还得去再判断一下锁，效率比较低
    public static Bank1 getInstance() {
        synchronized (Bank1.class) {
            if (instance == null) {
                return new Bank1();
            }
            return instance;
        }
    }
}

//二、性能较高的懒汉式线程安全写法

class Bank2 {
    private Bank2() {
    }

    private static Bank2 instance = null;

    //这种写法，做了双重判断，当第一个线程创建了实例对象之后，第二个线程看到他不是NULL 了，就不用去再次判断锁了
    public static Bank2 getInstance() {
        if (instance == null) {
            synchronized (Bank2.class) {
                if (instance == null) {
                    return new Bank2();
                }
            }
        }
        return instance;
    }
}