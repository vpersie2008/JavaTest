package com.jerry.thread5;

/*
 * 多个线程卖100张票问题
 * */
public class TicketTest {

    public static void main(String[] args) {
        Window window = new Window();
        //创建3个线程去处理一个窗口的票，因为只有一个对象，所以始终争取的是该window 中的ticket
        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);

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

                System.out.println(Thread.currentThread().getName() + "买票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}
