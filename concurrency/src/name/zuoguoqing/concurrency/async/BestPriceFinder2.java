/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file BestPriceFinder2.java
 * @author zuoguoqing
 * @date 2020年10月12日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zuoguoqing
 *
 */
public class BestPriceFinder2 {
    private List<Shop2> shops;
    private final int nThreads = 100;
    private final Executor executor;

    public BestPriceFinder2() {
        shops = new ArrayList<>();
        shops.add(new Shop2("BestShop"));
        shops.add(new Shop2("LetsSaveBig", Money.USD));
        shops.add(new Shop2("MyFavoriteShop"));
        shops.add(new Shop2("BuyItAll", Money.EUR));
        shops.add(new Shop2("ShopEasy"));
        shops.add(new Shop2("Shop6"));
        shops.add(new Shop2("Shop7"));
        shops.add(new Shop2("Shop8"));
        shops.add(new Shop2("Shop9"));

        executor = Executors.newFixedThreadPool(Math.min(shops.size(), nThreads),
                new ThreadFactory() {

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        return thread;
                    }
                });

    }

    /**
     * 顺序流同步获取价格信息
     * 
     * @param product
     * @return
     */
    public List<String> findPrices1(String product) {
        return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse)
                .map(Discount::applayDiscount).collect(Collectors.toList());
    }

    /**
     * 并行流获取价格信息
     * 
     * @param product
     * @return
     */
    public List<String> findPrices2(String product) {
        return shops.parallelStream().map(shop -> shop.getPrice(product)).map(Quote::parse)
                .map(Discount::applayDiscount).collect(Collectors.toList());
    }

    /**
     * CompletableFuture异步获取价格信息，配置线程池，异步流水线
     * 
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> Discount.applayDiscount(quote), executor)))
                .collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 返回流，可以用thenAccept方法对CompletableFuture注册其它行为；
     * @param product
     * @return
     */
    public Stream<CompletableFuture<String>> findPriceStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> Discount.applayDiscount(quote), executor)));
    }

    /**
     * thenCombine
     * 
     * @param shop
     * @param product
     * @return
     */
    public Future<Double> findPricesWithDiffMoney(Shop2 shop, String product) {

        return CompletableFuture.supplyAsync(() -> shop.getPrice(product)).thenCombine(
                CompletableFuture
                        .supplyAsync(() -> ExchangeRateService.getRate(shop.getMoney(), Money.RMB)),
                (price, rate) -> Quote.parse(price).getOriginalPrice() * rate);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        @SuppressWarnings("rawtypes")
        CompletableFuture[] futures = new BestPriceFinder2().findPriceStream("myPhone100S")
                .map(future -> future.thenAccept(s -> {
                    System.out.format("%s (Done in %d ms)\n", s, System.currentTimeMillis() - begin);
                }))
                .toArray(size -> new CompletableFuture[size]);
        
        // 异步线程均为守护线程，没有下行代码的话main线程执行完毕则程序结束
        // anyOf方法当最早的一个线程执行完毕则程序结束；
        CompletableFuture.allOf(futures).join();
        long duration = System.currentTimeMillis() - begin;
        System.out.println("All done in " + duration + " ms.");

        
        // try {
        // Double price = new BestPriceFinder2()
        // .findPricesWithDiffMoney(new Shop2("ForeignShop", Money.EUR),
        // "myPhone20S")
        // .get();
        // System.out.println(price);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // } catch (ExecutionException e) {
        // e.printStackTrace();
        // }
    }

}
