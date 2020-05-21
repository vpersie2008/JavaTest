package com.jerry.thread1;

/**
 * 多线程的创建方式
 * 方式一、继承自Thread 类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread 类的 run()方法-> 将线程的执行操作声明到run()中
 * 3.创建Thread 类的子类对象
 * 4.通过此对象调用start() 方法
 * 问题一、我们不能通过run方法启动线程，而是应该通过start方法启动线程。
 * 问题二、如何启动多个线程？必须重新 new 一个新的thread对象，再调用start()方法
 * 问题三、多线程之间不是顺序执行的，是随机执行的
 */

public class ThreadTest2 {
    public static void main(String[] args) {
        //3.创建Thread 类的子类对象
        MyThread thread1 = new MyThread();
        //4.调用对象，启动线程
        thread1.start();

        //重新new一个对象，新起一个子线程
        MyThread thread2 = new MyThread();
        thread2.start();

        //重新new一个对象，新起一个子线程
        MyThread thread3 = new MyThread();
        thread3.start();
    }
}




