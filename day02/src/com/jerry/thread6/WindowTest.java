package com.jerry.thread6;

/*
 * 一、问题：
 * 1.卖票过程出现了重票，错票问题，即出现了线程安全问题。
 * 2.出现重票和错票的原因，是因为在一个线程还没操作完，另一个线程过来再操作共享数据，即会出现线程安全问题。
 * 入戏代码，因为有线程共用资源，就会有线程安全问题
 * */
public class WindowTest {
    public static void main(String[] args) {
        Window win = new Window();
        Thread t1 = new Thread(win);
        Thread t2 = new Thread(win);
        Thread t3 = new Thread(win);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable {
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
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

