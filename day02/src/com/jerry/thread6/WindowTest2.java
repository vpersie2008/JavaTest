package com.jerry.thread6;

/*
 * 一、问题：
 * 同步代码块继承的方式，实现线程安全
 *
 * */
public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();
        w1.start();
        w2.start();
        w3.start();
    }
}


class Window2 extends Thread {
    private static int ticket = 100;
    //创建一个唯一对象，做为锁，锁可以使任何对象，但切记锁智能有一把,这时，如果不加 static ，因为继承方式创建了3个对象，所以这个锁不唯一。
    //必须使用 static 修饰，让object放到静态区，因为静态的先声明，只有一份，所以锁是唯一的
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            //1.通过继承的方式，这块可以使用Windo2.class 充当锁
            //2.通过继承的方式，通过唯一的静态变量obj来充当锁
            synchronized (Window2.class) {
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

