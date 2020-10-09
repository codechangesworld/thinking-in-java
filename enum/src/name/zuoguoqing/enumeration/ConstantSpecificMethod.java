/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file ConstantSpecificMethod.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午2:06:35
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午2:06:35
 */
public enum ConstantSpecificMethod {
    DATE_TIME {
        @Override
        public String info() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },

    CLASSPATH {

        @Override
        public String info() {
            return System.getenv("classpath");
        }

    },

    VERSION {

        @Override
        public String info() {
            return System.getProperty("java.version");
        }

    };

    public abstract String info();
    
    public static void main(String[] args) {
        Arrays.stream(ConstantSpecificMethod.values()).forEach(a -> {
            System.out.println(a.info());
        });
    }
}
