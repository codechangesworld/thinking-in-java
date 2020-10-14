/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file BestPriceFinder.java
 * @author zuoguoqing
 * @date 2020年10月11日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author zuoguoqing
 *
 */
public class BestPriceFinder {
    private List<Shop> shops = new ArrayList<Shop>();
    private final int nThreads = 100;
    private final Executor executor;

    public BestPriceFinder() {
        shops.add(new Shop("BestShop"));
        shops.add(new Shop("LetsSaveBig"));
        shops.add(new Shop("MyFavoriteShop"));
        shops.add(new Shop("BuyItAll"));
        shops.add(new Shop("ShopEasy"));
        shops.add(new Shop("Shop6"));
        shops.add(new Shop("Shop7"));
        shops.add(new Shop("Shop8"));
        shops.add(new Shop("Shop9"));

        executor = Executors.newFixedThreadPool(Math.min(shops.size(), nThreads), new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        
    }

    public void find() {
        long begin = System.currentTimeMillis();

        shops.stream().forEach(shop -> {
            long invocation = System.currentTimeMillis();
            Future<Double> futurePrice = shop.getPriceAsync("my favorite pruduct");
            long invocationTime = System.currentTimeMillis() - invocation;
            System.out.println("Invocation returned after " + invocationTime + " ms.");

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
        });

        long retrivalTime = System.currentTimeMillis() - begin;
        System.out.println("Price returned after " + retrivalTime + " ms.");
    }

    private static void doSomethingElse() {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 顺序流调用同步API
     * 
     * @param product
     * @return
     */
    public List<String> findPrices1(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getShopName(),
                shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 并行流调用同步API,使用默认的ForkJoinPool，线程数为Runtime.getRuntime().availableProcessor();
     * 
     * @param product
     * @return
     */
    public List<String> findPrices2(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f",
                shop.getShopName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * CompletableFuture实现异步调用同步API, 使用默认的ForkJoinPool，线程数为Runtime.getRuntime().availableProcessor();
     * 
     * @param product
     * @return
     */
    public List<String> findPrices3(String product) {
        List<CompletableFuture<String>> futures = shops
                .stream().map(shop -> CompletableFuture.supplyAsync(() -> String
                        .format("%s price is %.2f", shop.getShopName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * CompletableFuture实现异步调用同步API,使用定制Executor配置线程池
     * 
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> futures = shops
                .stream().map(shop -> CompletableFuture.supplyAsync(() -> String
                        .format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // new BestPriceFinder().find();

        long begin = System.currentTimeMillis();
        List<String> prices = new BestPriceFinder().findPrices("myPhone27S");
        long duration = System.currentTimeMillis() - begin;
        System.out.println(prices);
        System.out.println("Done in " + duration + " ms.");
    }

}
