/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.string
 * @file PrintMemAddr.java
 * @author zuoguoqing
 * @date 2017年11月18日
 * @version 
 */
package name.zuoguoqing.exercise.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoguoqing
 *
 */
public class PrintMemAddr {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<PrintMemAddr> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PrintMemAddr());
        }
        
        System.out.println(list);
    }

    @Override
    public String toString() {
        return "PrintMemAddr address: " + super.toString() + "\n";
    }
    
    
}
