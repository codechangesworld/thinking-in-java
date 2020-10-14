/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file Shop.java
 * @author zuoguoqing
 * @date 2020年10月11日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zuoguoqing
 *
 */
public class Shop {
    private String shopName;
    private Random random = new Random(7);
    
    public Shop(String shopName) {
        super();
        this.shopName = shopName;
    }
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private double caculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 同步方式API
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return caculatePrice(product);
    }
    
    /**
     * 传统异步方式
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync1(String product) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            return caculatePrice(product);
        });
    }
    
    /**
     * CompletableFuture实现异步
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() ->  {
            double price = caculatePrice(product);
            future.complete(price);
        }).start();;
        return future;
    }
    
    /**
     * 异步异常处理
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync3(String product) {
        CompletableFuture<Double> future = new CompletableFuture<Double>();
        new Thread(() -> {
            try {
                double price = caculatePrice(product);
                future.complete(price);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();;
        
        return future;
    }
    
    /**
     * 静态方法supplyAsync实现异步API
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> caculatePrice(product));
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long begin = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite pruduct");
        
        doSomethingElse();
        double price = 0D;
        try {
            price = futurePrice.get(3000, TimeUnit.MILLISECONDS);
            System.out.format("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        
        long retrivalTime = System.currentTimeMillis() - begin;
        System.out.println("Price returned after " + retrivalTime + " ms."); 
    }

    private static void doSomethingElse() {
        // TODO Auto-generated method stub
        
    }

}
