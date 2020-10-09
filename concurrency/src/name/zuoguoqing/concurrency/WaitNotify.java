/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file WaitNotify.java
 * @author zuoguoqing
 * @date 2017年11月29日
 * @version 
 */
package name.zuoguoqing.concurrency;

import java.time.LocalTime;

/**
 * @author zuoguoqing
 *
 */
public class WaitNotify {
    private static Object lock = new Object();
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        
        thread1.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        thread2.start();

    }
    
    
    static class Task1 implements Runnable {

        @Override
        public void run() {
            System.out.println(LocalTime.now().toString() + "  Task1 : Before lock");
            synchronized(lock) {
                try {
                    System.out.println(LocalTime.now().toString() + "  Task1 : wait ...");
                    lock.wait(3000);
                    System.out.println(LocalTime.now().toString() + "  Task1 : wake up!");
                } catch (InterruptedException e) {
                    System.out.println(LocalTime.now().toString() + "  Task1 : Catch Exception in lock block");
                    e.printStackTrace();
                }
            }
            System.out.println(LocalTime.now().toString() + "  Task1 : After lock");
        }
        
    }
    
    static class Task2 implements Runnable {

        @Override
        public void run() {
            System.out.println(LocalTime.now().toString() + "  Task2 : Before lock");
            synchronized(lock) {
                try {
                    System.out.println(LocalTime.now().toString() + "  Task2 : sleep for 1000 ms...");
                    Thread.sleep(1000);
                    lock.notify();
                } catch (InterruptedException e) {
                    System.out.println(LocalTime.now().toString() + "  Task2 : Catch Exception in lock block");
                    e.printStackTrace();
                }
            }
            System.out.println(LocalTime.now().toString() + "  Task2 : After lock");
        }
        
    }

}
