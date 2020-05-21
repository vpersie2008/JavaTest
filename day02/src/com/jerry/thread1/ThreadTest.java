package com.jerry.thread1;

/**
 * 多线程的创建方式
 * 方式一、继承自Thread 类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread 类的 run()方法-> 将线程的执行操作声明到run()中
 * 3.创建Thread 类的子类对象
 * 4.通过此对象调用start() 方法
 * 问题一、我们不能通过run方法启动线程，而是应该通过start方法启动线程。
 */

public class ThreadTest {
    public static void main(String[] args) {
        //3.创建Thread 类的子类对象
        MyThread thread = new MyThread();

        //4.调用对象，启动线程
        thread.start();

        //启动线程后，主线程往下继续走,所以先打印出这里
        //System.out.println("Main Thread...");
        mainTest();
    }

    private static void mainTest() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println("MainThread: " + Thread.currentThread().getName() + " Number: " + i);
            }
        }
    }
}

//1.创建一个继承自Thread 的子类
class MyThread extends Thread {
    //2.重写父类中Thread 的run方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("SubThread: " + Thread.currentThread().getName() + " Number: " + i);
            }
        }
    }
}



