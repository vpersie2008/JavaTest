package com.jerry.thread6;

import java.util.concurrent.locks.ReentrantLock;

/*
 *解决线程安全问题方式三：Lock 锁 -- JDK 5.0 中新增的方法
 * 使用ReentrantLock
 * 1.面试题：synchronized 和 lock方式，有什么不同？
 * 相同点：二者都可以解决线程安全问题
 * 不同点：lock 方式，是手动加锁和解锁。synchronized 是自动解锁的，他是在执行完同步方法或同步代码块才会自动释放同步监视器（自动解锁）。
 * 手动加锁，解锁，更加灵活
 * 2.面试题：如何解决线程安全问题？
 * 1)加同步代码块synchronized 关键字
 * 2)使用同步方法 ，即加synchronized 关键字的方法
 * 3)使用ReentrantLock 手动加锁，手动解锁
 *
 *
 * */
public class WindowTest5 {
    public static void main(String[] args) {
        Window5 win = new Window5();
        Thread t1 = new Thread(win);
        Thread t2 = new Thread(win);
        Thread t3 = new Thread(win);
        t1.start();
        t2.start();
        t3.start();
    }
}


class Window5 implements Runnable {
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                //手动加锁
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                //手动解锁
                lock.unlock();
            }
        }
    }
}

