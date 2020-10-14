/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file Discount.java
 * @author zuoguoqing
 * @date 2020年10月12日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoguoqing
 *
 */
public class Discount {
    public enum Code {
        NONE(0), SIVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int p) {
            percentage = p;
        }

        public int getPercentage() {
            return percentage;
        }
    }

    public static String applayDiscount(Quote quote) {
        double currentPrice = applay(quote.getOriginalPrice(), quote.getDiscount());
        return String.format("%s price is: %.2f", quote.getShopName(), currentPrice);
    }

    /**
     * 计算折扣价, 远程服务，有延时
     * @param originalPrice
     * @param discount
     * @return
     */
    private static double applay(double originalPrice, Code discount) {
        delay();
        return originalPrice * (100 - discount.getPercentage()) / 100;
    }

    /**
     * 模拟折扣服务延时
     */
    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
