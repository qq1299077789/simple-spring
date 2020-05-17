package com.ruixin.config;

import com.ruixin.bean.BeanObject;
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取Bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        BeanObject beanObject = beanFactory.get(beanName);
        if(beanObject == null){
            return null;
        }
        return beanObject.getBean();
    }

    /**
     *  加载bean
     */
    public void load(Class<?> clz) throws SpringException {
        AnnotationConfig annotationConfig = new AnnotationConfig(this);
        annotationConfig.doConfid(clz);
        //初始化环境
        environment.init();
        annotationConfig.load();
    }

    public static ApplicationContext builder(Class<?> clz) throws SpringException {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.environment = new Environment();
        applicationContext.beanFactory = new BeanFactory();
        applicationContext.load(clz);
        return applicationContext;
    }
}
