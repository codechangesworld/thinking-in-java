/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.annotation
 * @file DBString.java
 * @author ZuoGuoqing
 * @date 2017年11月29日 下午2:15:03
 * @version 1.0
 */
package name.zuoguoqing.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月29日 下午2:15:03
 */
public @interface DBString {
    int value() default 0;
    
    String name() default "";
    
    Constraints constraints() default @Constraints;
}
