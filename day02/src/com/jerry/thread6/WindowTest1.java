package com.jerry.thread6;

/*
 * 一、问题：
 * 1.卖票过程出现了重票，错票问题，即出现了线程安全问题。
 * 2.出现重票和错票的原因，是因为在一个线程还没操作完，另一个线程过来再操作共享数据，即会出现线程安全问题。
 * 3.如何解决？当一个线程在操作共享数据时，当前线程锁定资源，其他线程不能操作该共享数据，这种情况即使该线程阻塞，其他线程也不允许操作共享数据。
 * 二、Java 通过如下两种方式来解决线程安全问题
 * 1.同步代码块
 * synchronized（同步监视器）{
 *   //需要被同步的代码
 * }
 * 什么是需要被同步的代码？即操作共享数据的代码
 * 什么是共享数据？多个线程共同操作的变量。比如该例子中的ticket
 * 什么是同步监视器？形象的说就是“锁”。任何一个类的对象，都可以充当锁，但切记锁是惟一的。
 * 切记：多个线程公用一把锁，锁是唯一的。
 *
 * 加了同步监视器之后，在同步代码块中，智能有一个线程操作，其他线程等待，所以这块相当于单线程的，所以效率比较低一些。
 * 在使用继承方式来创建多线程时，要慎用this来充当同步监视器，考虑使用当前类充当同步监视器，如 : Window2.class
 *
 * 2.同步方法
 * 如果操作共享数据的代码完整的声明再一个方法中，我们不妨将此方法声明为同步的。
 * 在方法声明处加 synchronized 标识符
 *
 *
 * */
public class WindowTest1 {
    public static void main(String[] args) {
        Window1 win = new Window1();
        Thread t1 = new Thread(win);
        Thread t2 = new Thread(win);
        Thread t3 = new Thread(win);
        t1.start();
        t2.start();
        t3.start();
    }
}


class Window1 implements Runnable {
    private int ticket = 100;
    //创建一个唯一对象，做为锁，锁可以使任何对象，但切记锁智能有一把
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            //这里也可以用this 替换obj，因为我们只new了一个Window
            synchronized (obj) {
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
            }
        }
    }
}

