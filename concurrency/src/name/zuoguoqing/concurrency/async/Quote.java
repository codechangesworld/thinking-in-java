/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency.async
 * @file Quote.java
 * @author zuoguoqing
 * @date 2020年10月12日
 * @version 
 */
package name.zuoguoqing.concurrency.async;

import name.zuoguoqing.concurrency.async.Discount.Code;

/**
 * 商品折扣解析类
 * 
 * @author zuoguoqing
 *
 */
public class Quote {
    private String shopName;
    private double originalPrice;
    private Money money;
    private Discount.Code discount;

    public Quote(String shopName, double originalPrice, Money money, Code discount) {
        super();
        this.shopName = shopName;
        this.originalPrice = originalPrice;
        this.money = money;
        this.discount = discount;
    }

    public String getShopName() {
        return shopName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public Discount.Code getDiscount() {
        return discount;
    }
    
    public Money getMoney() {
        return money;
    }

    /**
     * 解析商店返回的价格折扣信息
     * @param string
     * @return
     */
    public static Quote parse(String string) {
        String[] strings = string.split(":");

        return new Quote(strings[0], Double.parseDouble(strings[1]), Money.valueOf(strings[2]),
                Discount.Code.valueOf(strings[3]));
    }
}
