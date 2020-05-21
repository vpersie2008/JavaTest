package com.jerry.thread6;

/*
 * 1. 使用同步方法方式，声明线程安全的同步方法
 * 2.同步方法
 * 如果操作共享数据的代码完整的声明再一个方法中，我们不妨将此方法声明为同步的。
 * 在方法声明处加 synchronized 标识符
 *
 *
 * */
public class WindowTest3 {
    public static void main(String[] args) {
        Window3 win = new Window3();
        Thread t1 = new Thread(win);
        Thread t2 = new Thread(win);
        Thread t3 = new Thread(win);
        t1.start();
        t2.start();
        t3.start();
    }
}


class Window3 implements Runnable {
    private int ticket = 100;

    //创建一个唯一对象，做为锁，锁可以使任何对象，但切记锁智能有一把
    @Override
    public void run() {
        while (true) {
            this.show();
            if (ticket <= 0) {
                break;
            }
        }
    }

    private synchronized void show() {//同步监视器中，这块默认的锁就是this
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
            ticket--;
        }
    }
}

