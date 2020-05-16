package com.ruixin.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:Bean对象
 */
public class BeanObject {

    //是否已经完全注入
    private boolean isRender = false;

    //bean对象
    private Object bean;

    //bean的别名
    private String beanName;

    //这个bean的class对象
    private Class<?> beanClass;

    //这个bean依赖的bean
    private List<String> dependsBean = new ArrayList<String>();

    public BeanObject(String beanName,Object bean){
        this.bean = bean;
        this.beanName = beanName;
        this.beanClass = bean.getClass();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public boolean isRender() {
        return isRender;
    }

    public void setRender(boolean render) {
        isRender = render;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public List<String> getDependsBean() {
        return dependsBean;
    }

    public void setDependsBean(List<String> dependsBean) {
        this.dependsBean = dependsBean;
    }

    @Override
    public String toString() {
        return "BeanObject{" +
                "isRender=" + isRender +
                ", bean=" + bean +
                ", dependsBean=" + dependsBean +
                '}';
    }
}
