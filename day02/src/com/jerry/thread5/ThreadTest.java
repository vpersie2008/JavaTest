package com.jerry.thread5;

/*
 *
 * 创建线程的实现方法二，实现Runnable 接口的方式
 * 1. 创建一个实现了Runnable 接口的类
 * 2. 实现Runnable 中的抽象方法： run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread 类的构造器中，创建Thread 类的对象
 * 5. 通过Thread 类的对象调用start()，用于启动线程
 *
 * */
public class ThreadTest {
    public static void main(String[] args) {
        Mthread mthread = new Mthread();

        //创建线程1
        Thread t1 = new Thread(mthread);
        t1.start();

        //创建线程2
        Thread t2 = new Thread(mthread);
        t2.start();

        //创建线程3
        Thread t3 = new Thread(mthread);
        t3.start();

        //线程4，简化写法,匿名对象
        new Thread(mthread).start();

    }
}

//使用实现Runnable 接口去实现多线程
class Mthread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
