package com.jerry.thread9;

/*
* 如下代码，演示调用notify和wait，必须在当前锁的对象下
* */
public class ThreadNotifyTest2 {
    public static void main(String[] args) {
        Number2 num = new Number2();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);
        t1.start();
        t2.start();
    }
}

class Number2 implements Runnable {
    private int number = 1;

    //使用obj对象充当当前锁。
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {

                obj.notify();

                // this.notifyAll();//唤醒所有正在被wait的线程
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}