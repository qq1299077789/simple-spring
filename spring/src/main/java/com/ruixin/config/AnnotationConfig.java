package com.ruixin.config;

import com.ruixin.annotation.Bean;
import com.ruixin.annotation.BeanScan;
import com.ruixin.bean.BeanObject;
import com.ruixin.exception.SpringException;
import com.ruixin.util.BeanUtil;
import com.ruixin.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruixin on 2020/5/17.
 * Description:注解实现类
 */
public class AnnotationConfig {

    private ApplicationContext applicationContext;

    public AnnotationConfig(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 处理启动类上的注解
     * @param clz
     */
    public void doConfid(Class<?> clz){
        BeanScan beanScan = (BeanScan) BeanUtil.getAnnotation(clz, BeanScan.class);
        Environment environment = applicationContext.getEnvironment();
        String[] beanPackage = beanScan.beanPackage();
        if(beanPackage!=null&&beanPackage.length>0){
            environment.setBeanPackage(Arrays.asList(beanPackage));
        }
        String configFile = beanScan.configFile();
        if(!StringUtil.isBlackOrNull(configFile)){
            environment.setConfigFile(configFile);
        }
    }

    /**
     * 加载Bean
     */
    public void load() throws SpringException {
        ClassLoader classLoader = new ClassLoader();
        classLoader.load(applicationContext.getEnvironment().getBeanPackage());
        List<Class<?>> classes = classLoader.getClasses();
        for (Class<?> clz:classes) {
            if(BeanUtil.isBean(clz)){
                String beanName = BeanUtil.getBeanName(clz);
                Object bean = BeanUtil.newBean(clz);
                BeanObject beanObject = new BeanObject(beanName,bean);
                applicationContext.getBeanFactory().set(beanName,beanObject);
            }
        }
    }
}
