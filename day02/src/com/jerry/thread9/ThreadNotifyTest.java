package com.jerry.thread9;

/*
 * 线程通信的例子：使用两个线程打印1-100，交替打印，如：线程1，再线程2，再线程1，再线程2
 * 1.wait() 方法，用于阻塞当前线程，这个方法执行时会释放当前锁，而 sleep() 不会释放当前锁。
 * 2.使用notify() 方法，通知正在被阻塞的线程，将其唤醒,如果有多个线程被wait()，则优先唤醒优先级高的那个。
 * 3.使用notifyAll() 方法，通知并唤醒所有被wait()的线程。
 *
 * 说明：
 * 1.这几个方法，必须都在同步代码块，或者同步方法中。
 * 2. 这三个方法，调用者必须是同步代码块或同步同步方法中的同步监视器。
 *  例如，如下代码中调用notify和wait都是在this对象下的，即用的是this当锁的，如果重新起一个obj 对象，this还是锁，使用obj.wait() 肯定是不行的
 *  简单的来说，就是调用notify和wait的对象，必须是当前锁对象。
 * 3.这三个方法，不是在Thread 类中定义的，这三个方法，是在Object 类中定义的。因为任何一个对象都有这几个方法，即这几个方法在最终父类中。
 *
 * 面试题：
 * sleep 和wait有什么异同？
 * 1.相同点：一旦执行方法，都可以使得当前线程进入阻塞状态。
 * 2.不同点：
 *      1)两个方法声明的位置不同，Thread 类中声明sleep()，Object 类中声明wait()
 *      2) 调用的要求不同： sleep() 可以在任何需要的场景下调用，wait() 必须在同步代码块或同步方法中。
 *      3) 如果两个方法都在同步监视器或同步方法中，sleep 不会释放锁，而wait会释放锁。
 * */
public class ThreadNotifyTest {
    public static void main(String[] args) {
        Number num = new Number();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);
//        Thread t3 = new Thread(num);
        t1.start();
        t2.start();
//        t3.start();
    }
}

class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //唤醒被阻塞的线程，之前线程1被阻塞，线程2进来notify线程1唤醒
                this.notify();

                // this.notifyAll();//唤醒所有正在被wait的线程
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    //wait当前线程，即阻塞当前线程
                    try {
                        wait();//一旦执行wait，会释放锁，例如当线程2进来后走到这里，释放了锁之后，线程1才能再次进来
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}