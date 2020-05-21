package com.jerry.thread10;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * 四、使用线程池来创建线程
 * 线程池的好处：
 * 1.提高了响应速度(减少了创建线程的时间)
 * 2.降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
 * 3.便于管理，如可设置如下属性管理线程池
 *   1) corePoolSize 线程池大小
 *   2) maximumPoolSize 最大线程数
 *   3) keepAliveTime 线程没有任务时，最多保持多长时间后会终止
 * */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //1.创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //2.设置线程池属性
        ThreadPoolExecutor executor1 = (ThreadPoolExecutor) service;
        executor1.setCorePoolSize(10);

        //3.执行当前线程，适合用于Runnable，Callable 要用submit
        service.execute(new NumThread1());//执行线程1，用于输出偶数
        service.execute(new NumThread2());//执行线程2，用于输出奇数
        //4.关闭当前线程，线程池不用了，则关闭当前线程
        service.shutdown();
    }
}

class NumThread1 implements Runnable {
    //输出偶数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumThread2 implements Runnable {
    //输出奇数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
