package com.jerry.thread2;

/*
 * 练习：创建两个分线程，其中一个打印100以内的奇数，另一个打印100以内的偶数
 * 思路：分别创建两个子类，继承Thread
 * */

public class ThreadExercise {
    public static void main(String[] args) {
        //1.创建线程1
        MyThread1 thread1 = new MyThread1();
        thread1.start();

        //创建线程2
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}


//创建Thread1，打印偶数
class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("SubThread: " + Thread.currentThread().getName() + " Number: " + i);
            }
        }
    }
}

//创建Thread2打印奇数
class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println("SubThread: " + Thread.currentThread().getName() + " Number: " + i);
            }
        }
    }
}

