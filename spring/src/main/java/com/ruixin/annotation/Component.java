package com.ruixin.annotation;

import java.lang.annotation.*;

/**
 * Created by RuiXin on 2020/5/16.
 * Description:Component注解，依赖Bean注解
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Bean
public @interface Component {

    String value() default "";

    String name() default "";
}
