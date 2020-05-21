package com.jerry.thread3;

/*
 * 线程中的常用方法
 *
 * 1. start()：启动当前线程，调用当前线程中的run() 方法
 * 2. run(): 通常需要重写Thread 类中的此方法，将创建的线程要执行的操作声明再此方法中
 * 3. currentThread()：静态方法，返回执行当前代码的线程
 * 4. getName();获取当前线程的名字,也可以根据构造器传参方式命名
 * 5. setName();设置当前线程的名字
 * 6. yield(); 释放CPU 的执行权
 * 7. join(); 当前线程先暂停，切换到另一个线程，等另一个线程执行完，才能执行当前线程
 * 8. stop(); 已过时。用于强制结束当前线程
 * 9. sleep(long millitime)，线程挂起多少毫秒
 * */

public class ThreadTest {
    public static void main(String[] args) {
        //线程命名方式，可以根据构造函数传参方式
        HelloThread thread = new HelloThread("Thread: 1");
        //可以通过setName 方法,给线程命名
        //thread.setName("Thread 1");
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

class HelloThread extends Thread {
    HelloThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    // sleep方法，是会throws 一个异常，所以必须用try-catch
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }

            if (i % 20 == 0) {
                //释放当前CPU 执行权，一旦释放，则会去别的线程执行。
                // 这种情况，不是CPU 去切换线程，而是我们去手动切换线程，切换到别的线程是随机的
                //有可能释放执行权后，再次执行到该线程
                yield();
            }
        }
    }
}
