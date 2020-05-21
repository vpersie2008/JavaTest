package com.jerry.thread6;

/*
 *使用继承的方式，使用同步方法，来实现线程安全
 * 同步监视器，不显式声明，
 * 1.非静态同步方法，同步监视器是this
 * 2.静态同步方法，同步监视器是当前类本身，即ClassName.class
 * */
public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();
        w1.start();
        w2.start();
        w3.start();
    }
}


class Window4 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
            if (ticket <= 0) {
                break;
            }
        }
    }

    //这里不加static，它的锁不唯一，因为同步非静态方法的锁为this，此时创建了3个对象，所以有3个this
    // 加上static，让他成为一个静态方法，静态方法他只有一份，它的锁是Window4.class，即当前类.class
    private static synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
            ticket--;
        }
    }

}

