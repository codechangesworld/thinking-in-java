/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file Shop2.java
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
public class Shop2 {
    private String shopName;
    private Money money;
    private Random random = new Random();
    
    public Shop2(String shopName) {
        this(shopName, Money.RMB);
    }
    
    public Shop2(String shopName, Money money) {
        super();
        this.shopName = shopName;
        this.money = money;
    }
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    /**
     *  计算商品价格
     * @param product
     * @return
     */
    private double caculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            long delay = 500 + random.nextInt(2000);
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取商品价格信息
     * @param product
     * @return
     */
    public String getPrice(String product) {
        double price = caculatePrice(product);
        Discount.Code discount = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        
        return String.format("%s:%.2f:%s:%s", shopName, price, money, discount);
    }
}
