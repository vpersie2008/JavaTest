
/*
 * 创建线程的方式三、实现Callable 接口，JDK 5.0 中新增的。
 * 如何理解Callable 接口更强大？
 * 1. call() 方法是有返回值得。
 * 2. call() 可以抛出异常，被外面的操作捕获，获取异常信息。
 * 3. Callable 中是支持泛型的，这个泛型就是用futureTask.get()的返回值。
 * */
package com.jerry.thread10;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCallableTest {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        NumThread numThread = new NumThread();

        //4.将此Callable实现类的对象(numThread)传递FutureTask构造其中，创建FutureTask对象。
        FutureTask<Integer> futureTask = new FutureTask<Integer>(numThread);

        //5.将FutureTask创建的对象，传递到Thread构造器中，在start该线程。
        new Thread(futureTask).start();

        //6.如果关心线程中call方法的返回值，则可以用个futureTask.get() 来抓取返回值。
        try {
            //拿到其返回值
            Integer sum = futureTask.get();
            System.out.println("总和为: " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//1.创建一个实现Callable 的实现类
class NumThread implements Callable<Integer> {

    //2.实现call方法，将此线程需要执行的操作声明到call方法中，注意该方法是有返回值的。
    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }

        return sum;
    }
}