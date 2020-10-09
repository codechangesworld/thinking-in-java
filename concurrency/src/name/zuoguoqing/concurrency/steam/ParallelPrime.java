/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.steam
 * @file ParallelPrime.java
 * @author zuoguoqing
 * @date 2020年10月9日
 * @version 
 */
package name.zuoguoqing.concurrency.steam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author zuoguoqing
 *
 */
public class ParallelPrime {
    static final int NUM_COUNT = 100_000;

    public static boolean isPrime(long num) {
        return LongStream.rangeClosed(2, (long) Math.sqrt(num)).noneMatch(i -> num % i == 0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        List<String> primes = LongStream.iterate(2, i -> i + 1)
                .parallel()
                .filter(ParallelPrime::isPrime)
                .limit(NUM_COUNT)
                .mapToObj(Long::toString)
                .collect(Collectors.toList());
        
        long end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - begin) + " ms");
        try {
            Files.write(Paths.get("primes.txt"), primes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
                
    }

}
