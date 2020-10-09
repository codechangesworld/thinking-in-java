/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.steam
 * @file Summing.java
 * @author zuoguoqing
 * @date 2020年10月9日
 * @version 
 */
package name.zuoguoqing.concurrency.steam;

import java.util.Arrays;
import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * @author zuoguoqing
 *
 */
public class Summing {

    /**
     * 求和测试器
     * 
     * @param id
     * @param checkValue
     * @param operation
     */
    static void summingTest(String id, long checkValue, LongSupplier operation) {
        System.out.print("id : " + id);
        long begin = 0L;
        long end = 0L;
        long deration = Long.MAX_VALUE;
        long result;
        result = operation.getAsLong();
        // 多次运行进行数据预热优化
        if (checkValue == result) {
            for (int i = 0; i < 5; i++) {
                begin = System.currentTimeMillis();
                result = operation.getAsLong();
                end = System.currentTimeMillis();
                deration = ((end - begin) < deration) ? (end - begin) : deration;
            }
            System.out.println(", cost: " + deration + " ms");
        } else {
            System.out.println(", checkValue: " + checkValue + ", result: " + result);
        }
    }

    /**
     * 
     */
    static void summing1() {
        final int SIZE = 10_000_000;
        final long CHECKER = (long) SIZE * (SIZE + 1) / 2;
        // 7 ms
        summingTest("Sum Stream", CHECKER, () -> LongStream.rangeClosed(1, SIZE).sum());
        // 5 ms
        summingTest("Sum Stream Paralell", CHECKER,
                () -> LongStream.rangeClosed(1, SIZE).parallel().sum());

        // 21 ms
        summingTest("Sum Iterated", CHECKER,
                () -> LongStream.iterate(1, i -> i + 1).limit(SIZE).sum());
        // 219 ms, cause OutOfMemoryError when SIZE is a litter large
        summingTest("Sum Iterated Paralell", CHECKER,
                () -> LongStream.iterate(1, i -> i + 1).parallel().limit(SIZE).sum());
    }

    static long basicSum(long[] array) {
        int length = array.length;
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += i;
        }

        return sum;
    }

    /**
     * 先填充数组，再进行计算
     */
    static void summing2() {
        final int SIZE = 10_000_000;
        final long CHECKER = (long) SIZE * (SIZE + 1) / 2;

        long[] array = new long[SIZE + 1];
        Arrays.parallelSetAll(array, i -> i);

        // 14 ms
        summingTest("Sum Array Stream", CHECKER, () -> Arrays.stream(array).sum());
        // 12 ms
        summingTest("Sum Array Stream Parallel", CHECKER,
                () -> Arrays.stream(array).parallel().sum());
        // 5 ms
        summingTest("Sum Array Basic", CHECKER, () -> basicSum(array));
        // 29 ms
        summingTest("Sum Array Prifex", CHECKER, () -> {
            Arrays.parallelPrefix(array, Long::sum);
            return array[SIZE];
        });
    }

    static long basicSum(Long[] array) {
        int length = array.length;
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += i;
        }

        return sum;
    }

    /**
     * 使用Long
     */
    static void summing3() {
        final int SIZE = 10_000_000;
        final long CHECKER = (long) SIZE * (SIZE + 1) / 2;

        Long[] array = new Long[SIZE + 1];
        Arrays.parallelSetAll(array, i -> (long) i);

        // 132 ms
        summingTest("Sum Array Stream", CHECKER,
                () -> Arrays.stream(array).reduce(Long::sum).get());
        // 94 ms
        summingTest("Sum Array Stream Parallel", CHECKER,
                () -> Arrays.stream(array).parallel().reduce(Long::sum).get());

        // 5 ms
        summingTest("Sum Array Basic", CHECKER, () -> basicSum(array));
        // 2326 ms
        summingTest("Sum Array Prifex", CHECKER, () -> {
            Arrays.parallelPrefix(array, Long::sum);
            return array[SIZE];
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
//         summing1();
         summing2();
//        summing3();
    }

}
