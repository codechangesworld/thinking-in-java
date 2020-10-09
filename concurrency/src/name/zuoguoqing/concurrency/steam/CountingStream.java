/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.steam
 * @file CountingStream.java
 * @author zuoguoqing
 * @date 2020年10月9日
 * @version 
 */
package name.zuoguoqing.concurrency.steam;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

/**
 * @author zuoguoqing
 *
 */
public class CountingStream {
    static class CountingTask implements Callable<Integer> {
        private final int id;

        public CountingTask(int id) {
            super();
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            int val = 0;
            for (int i = 0; i < 100; i++) {
                val++;
            }

            System.out.println(
                    "TaskId: " + id + ", " + Thread.currentThread().getName() + ", Result: " + val);

            return val;
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Total: " + 
                IntStream.rangeClosed(1, 10).parallel().mapToObj(CountingTask::new).map(ct -> {
                    try {
                        return ct.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }   
                    return null;
                }).reduce(0, Integer::sum));
        
    }

}
