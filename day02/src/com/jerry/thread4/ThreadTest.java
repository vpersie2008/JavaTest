package com.jerry.thread4;

/*
* 线程的优先级：分为如下等级
* MIN_PRIORITY = 1;
* NORM_PRIORITY = 5;
* MAX_PRIORITY = 10;
* 1.修改优先级：thread.setPriority(1~10);
* 设置了高优先级的线程，只是优先去执行它，并不是说他必须全部执行完，才会切换到低优先级的线程。
* 从概率上来讲，CPU 优先执行高优先级的线程
 */

public class ThreadTest {
    public static void main(String[] args) {
        ThreadPriority thread = new ThreadPriority("Sub Thread: 1");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

class ThreadPriority extends Thread {
    ThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}
