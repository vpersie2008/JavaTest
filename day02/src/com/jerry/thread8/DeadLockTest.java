package com.jerry.thread8;

/*
 * 一、死锁问题：
 * 什么是死锁？
 * 1.不同线程分别占用对方的需要的同步资源不放弃，都在等待对方放弃自己所需要的同步资源，就形成了线程的死锁
 * 2.出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 *
 *  二、怎样解决死锁？
 * 1.专门的算法，原则避免。
 * 2.尽量减少同步资源的定义
 * 3.尽量避免同步嵌套
 * */
public class DeadLockTest {
    //死锁问题
    //产生死锁的原因，sleep之后，死锁概率加大，两个锁互相等待对方释放锁，而又拿着对方的锁不放，就导致死锁
    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        //使用继承方式创建线程
        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");
                    sleepTime(100);

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        //使用实现Runnable 接口的方式创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");
                    sleepTime(100);
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }

    private static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
