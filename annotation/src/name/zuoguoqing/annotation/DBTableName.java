/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.annotation
 * @file TableName.java
 * @author ZuoGuoqing
 * @date 2017年11月29日 下午2:06:03
 * @version 1.0
 */
package name.zuoguoqing.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月29日 下午2:06:03
 */
public @interface DBTableName {
    String name() default "";
}
