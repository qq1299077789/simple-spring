package com.ruixin.annotation;

import java.lang.annotation.*;

/**
 * Created by ruixin on 2020/5/16.
 * Description:Bean
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

    String value() default "";

    String name() default "";

}
