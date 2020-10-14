/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file ExchangeRateService.java
 * @author zuoguoqing
 * @date 2020年10月12日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoguoqing
 *
 */
public class ExchangeRateService {
    private static Random random = new Random();
    
    public static double getRate(Money original, Money target) {
        delay();
        return random.nextDouble() * random.nextInt(1000);
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
