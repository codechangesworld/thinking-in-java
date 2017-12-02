/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file ThreadTest.java
 * @author zuoguoqing
 * @date 2017年11月29日
 * @version 
 */
package name.zuoguoqing.concurrency;

/**
 * @author zuoguoqing
 *
 */
public class ThreadTest {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("starting...");
        Thread.sleep(2000);
        
        System.out.println("Thread.interrupted() : " + Thread.interrupted());
        System.out.println("Thread.interrupted() : " + Thread.interrupted());
        
    }
    
    

}
