package com.ruixin.test;

import com.ruixin.annotation.BeanScan;
import com.ruixin.config.SpringApplication;
import com.ruixin.exception.SpringException;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:Bean测试类
 */
@BeanScan(beanPackage = {"com.ruixin.test"})
public class Test {

    public static void main(String[] args) throws SpringException {
        SpringApplication springApplication = SpringApplication.builder(Test.class);
        Object test = springApplication.getBean("test");
        System.err.println(test);
    }
}
