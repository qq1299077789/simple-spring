package com.ruixin.config;

import com.ruixin.exception.SpringException;
import com.ruixin.factory.BeanFactory;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:Applicaiton上下文
 */
public class ApplicationContext {

    //环境
    private Environment environment;

    //bean工厂
    private BeanFactory beanFactory;

    public static ApplicationContext builder(String configFile) throws SpringException {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.environment = Environment.builder(configFile);
        applicationContext.beanFactory = new BeanFactory();
        return applicationContext;
    }
}
