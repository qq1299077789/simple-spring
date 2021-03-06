package com.ruixin.annotation;

import java.lang.annotation.*;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:扫描Bean的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE})
public @interface BeanScan {

    //扫描的Bean包
    String[] beanPackage() default {};

    String configFile() default "";
}
