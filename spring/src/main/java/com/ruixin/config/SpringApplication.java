package com.ruixin.config;

import com.ruixin.exception.SpringException;

/**
 * Created Ruixin on 2020/5/16.
 * Description:SpringApplication启动类
 */
public class SpringApplication {

    private ApplicationContext applicationContext;

    /**
     * 获取Bean
     * @param beanName
     */
    public Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static SpringApplication builder(Class<?> clz) throws SpringException {
        SpringApplication springApplication = new SpringApplication();
        springApplication.applicationContext = ApplicationContext.builder(clz);
        return springApplication;
    }

}
