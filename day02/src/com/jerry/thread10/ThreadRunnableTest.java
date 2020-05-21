package com.jerry.thread10;

public class ThreadRunnableTest {
    public static void main(String[] args) {
        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread t1 = new Thread(myRunnableThread);
        t1.start();
    }
}


class MyRunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}