/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file CompletableFutureExample.java
 * @author zuoguoqing
 * @date 2020年10月14日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureExample {

    // custom executor
    private static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }

    });

    private static void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(500 + new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void enoughSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // difference between get and join method
    public static void joinGetExample() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            @SuppressWarnings("unused")
            int i = 1 / 0;
            return 100;
        });
        // future.join();
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
        System.out.println("completedFuture");
    }

    public static void runAsyncExample() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            // 异步执行，线程是默认ForkJoinPool线程池的守护线程
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });

        assertFalse(cf.isDone());
        enoughSleep();
        assertTrue(cf.isDone());

        System.out.println("runAsync");
    }

    public static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            // 同步执行，线程是当前主线程
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));

        System.out.println("thenApply");
    }

    public static void thenApplyAsyncExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            // 异步执行，线程是默认ForkJoinPool线程池的守护线程
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
        System.out.println("thenApplyAsync");
    }

    public static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            // 确认线程名称是自定义指定字符串开头
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor"));
            // 异步执行，确认线程是自定义线程池的非守护线程
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
        executor.shutdown();

        System.out.println("thenApplyAsync");
    }

    public static void thenAcceptExample() {
        StringBuilder builder = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept Message").thenAccept(s -> {
            builder.append(s);
        });
        System.out.println(builder);
        assertTrue("Result is empty.", builder.length() > 0);
        System.out.println("thenAccept");
    }

    public static void thenAcceptAsyncExample() {
        StringBuilder builder = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync Message").thenAcceptAsync(s -> {
            builder.append(s);
        });
        cf.join();
        System.out.println(builder);
        assertTrue("Result is empty.", builder.length() > 0);
        System.out.println("thenAcceptAsync");
    }

    /**
     * 注意异步延时执行的顺序
     */
    public static void completeExceptionallyExample() {
        System.out.println("1: " + LocalTime.now());

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println("2: " + LocalTime.now());
            return s.toUpperCase();
        }, CompletableFuture.delayedExecutor(1000, TimeUnit.MILLISECONDS));

        System.out.println("3: " + LocalTime.now());
        // System.out.println(cf.join());
        // System.out.println(LocalTime.now());

        CompletableFuture<String> handle = cf.handle((s, t) -> {
            System.out.println("4: " + LocalTime.now());
            return t != null ? "message upon cancel" : "";
        });

        System.out.println("5: " + LocalTime.now());
        // cf是异步延时执行的，此处代码先于‘2：’处代码执行，导致‘2：’处异步任务未执行
        // 此处cf以抛出异常完成执行，然后执行下阶段‘4：’处的任务
        cf.completeExceptionally(new RuntimeException("complete exceptionally"));

        System.out.println("6: " + LocalTime.now());
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());

        System.out.println("7: " + LocalTime.now());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertEquals("complete exceptionally", e.getCause().getMessage());
        }

        System.out.println("8: " + LocalTime.now());
        assertEquals("message upon cancel", handle.join());

        System.out.println("completeExceptionally");
    }

    /**
     * cf正常完成，则cf2结果与cf相同；cf异常完成，则cf2触发cf.exceptionally方法，并返回其参数函数的返回值；
     */
    public static void cancelExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
                CompletableFuture.delayedExecutor(1000, TimeUnit.MILLISECONDS));

        // System.out.println(cf.join());
        CompletableFuture<String> cf2 = cf.exceptionally(throwable -> "canceled message");
        // System.out.println(cf2.join());

        assertTrue("Was not canceled", cf.cancel(true));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());

        System.out.println("cancel");
    }

    public static void applyToEither() {
        String str = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(str).thenApplyAsync(s -> {
            randomSleep();
            System.out.println("1: " + LocalTime.now());
            return s.toUpperCase();
        });
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(str).thenApplyAsync(s -> {
            randomSleep();
            System.out.println("2: " + LocalTime.now());
            return s.toLowerCase();
        });
        System.out.println("3: " + LocalTime.now());
        CompletableFuture<String> cf3 = cf1.applyToEither(cf2, s -> s + " from applyToEither");

        System.out.println(cf3.join());
        assertTrue(cf3.join().endsWith(" from applyToEither"));

        System.out.println("applyToEither");
    }

    public static void acceptEitherExample() {
        String str = "message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(str).thenApplyAsync(s -> {
            randomSleep();
            return s.toUpperCase();
        });
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(str).thenApplyAsync(s -> {
            randomSleep();
            return s.toLowerCase();
        });
        CompletableFuture<Void> cf3 = cf1.acceptEither(cf2, s -> result.append(s).append(" acceptEither"));
        cf3.join();
        System.out.println(result);
        assertTrue(result.toString().endsWith("acceptEither"));

        System.out.println("acceptEither");
    }

    public static void runAfterBothExample() {
        String string = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(string).thenApplyAsync(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(string).thenApplyAsync(String::toLowerCase),
                () -> result.append(string).append(" Done!"));

        assertTrue(result.toString().endsWith("Done!"));
    }

    public static void thenAcceptBothExample() {
        String string = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(string).thenApplyAsync(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(string).thenApplyAsync(String::toLowerCase),
                (s1, s2) -> result.append(string).append(s1).append(s2));

        assertTrue(result.length() == string.length() * 3);

        System.out.println("thenAcceptBoth");
    }

    public static void thenCombineExample() {
        String string = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(string).thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(string).thenApply(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));

        System.out.println("thenCombine");
    }

    public static void thenCombineAsyncExample() {
        String string = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(string).thenApplyAsync(s -> {
            randomSleep();
            return s.toUpperCase();
        }).thenCombine(CompletableFuture.completedFuture(string).thenApplyAsync(s -> {
            randomSleep();
            return s.toLowerCase();
        }), (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());

        System.out.println("thenCombineAsync");
    }

    public static void thenComposeAsyncExample() {
        String string = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(string).thenApplyAsync(s -> {
            randomSleep();
            return s.toUpperCase();
        }).thenCompose(upper -> CompletableFuture.completedFuture(string).thenApplyAsync(s -> {
            randomSleep();
            return s.toLowerCase();
        }).thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());

        System.out.println("thenCombposeAsync");
    }

    public static void anyOfExample() {
        List<String> messages = List.of("a", "b", "c");
        StringBuilder builder = new StringBuilder();
        List<CompletableFuture<String>> futures = messages.stream()
                .map(m -> CompletableFuture.completedFuture(m).thenApply(s -> {
                    randomSleep();
                    return s.toUpperCase();
                })).collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((result, throwable) -> {
                    assertTrue(isUpperCase((String) result));
                    builder.append(result);
                });
        assertTrue("Was empty", builder.length() > 0);
        System.out.println("anyOf");
    }
    
    private static boolean isUpperCase(String result) {
        return !result.chars().anyMatch(Character::isLowerCase);
    }
    
    public static void allOfExample() {
        List<String> messages = List.of("a", "b", "c");
        StringBuilder builder = new StringBuilder();
        List<CompletableFuture<String>> futures = messages.stream()
                .map(m -> CompletableFuture.completedFuture(m).thenApply(s -> {
                    randomSleep();
                    return s.toUpperCase();
                })).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((result, throwable) -> {
                    futures.forEach(f -> assertTrue(isUpperCase(f.getNow(null))));
                    builder.append(result);
                });
        assertTrue("Was empty", builder.length() > 0);

        System.out.println("allOf");
    }
    
    public static void allOfAsyncExample() {
        List<String> messages = List.of("a", "b", "c");
        StringBuilder builder = new StringBuilder();
        List<CompletableFuture<String>> futures = messages.stream()
                .map(m -> CompletableFuture.completedFuture(m).thenApplyAsync(s -> { // 此处使用异步执行
                    randomSleep();
                    return s.toUpperCase();
                })).collect(Collectors.toList());
        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((result, throwable) -> {
                    futures.forEach(f -> assertTrue(isUpperCase(f.getNow(null))));
                    builder.append(result);
                });
        // 需要等待异步任务完成
        all.join();
        assertTrue("Was empty", builder.length() > 0);

        System.out.println("allOfAsync");
    }
    
    public static void main(String[] args) {

        // joinGetExample();
        // completedFutureExample();
        // runAsyncExample();
        // thenApplyExample();
        // thenApplyAsyncExample();
        // thenApplyAsyncWithExecutorExample();
        // thenAcceptExample();
        // thenAcceptAsyncExample();
        // completeExceptionallyExample();
        // cancelExample();
        // applyToEither();
        // acceptEitherExample();
        // runAfterBothExample();
        // thenAcceptBothExample();
        // thenCombineExample();
        // thenCombineAsyncExample();
        // thenComposeAsyncExample();
        // anyOfExample();
        // allOfExample();
        allOfAsyncExample();

        System.out.println("All Done!");
    }
}
