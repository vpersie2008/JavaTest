package com.jerry.thread3;

/*
 *本例子演示join() 方法
 *  join()方法，用于切换当前线程的执行权到另一个线程，谁调它，就切换到谁
 *
 * */
public class ThreadTest2 {
    public static void main(String[] args) {
        //线程命名方式，可以根据构造函数传参方式
        HelloThread thread1 = new HelloThread("Thread: 1");
        //可以通过setName 方法,给线程命名
        //thread1.setName("Thread 1");
        thread1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            if (i == 20) {// 主线程，执行到20时候，切换到子线程，子线程执行完之后，主线程继续执行
                try {
                    //主线程的执行权，切换到thread1 子线程
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class HelloThread2 extends Thread {
    HelloThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }

            if (i == 20) {

            }
        }
    }
}