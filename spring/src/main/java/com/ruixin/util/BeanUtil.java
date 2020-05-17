package com.ruixin.util;

import com.ruixin.annotation.Bean;
import com.ruixin.exception.IExceptionType;
import com.ruixin.exception.SpringException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by Ruixin on 2020/5/17.
 * Description:bean工具类
 */
public class BeanUtil {

    /**
     * 获取类上面的注解
     * @param clz
     * @param annotationClz
     * @return
     */
    public static Annotation getAnnotation(Class<?> clz,Class<? extends Annotation> annotationClz){
        Annotation annotation = clz.getAnnotation(annotationClz);
        return annotation;
    }

    /**
     * 判断一个类是否是bean
     * @param clz
     * @return
     */
    public static boolean isBean(Class<?> clz){
        Annotation beanAnno = getBeanAnno(clz);
        return beanAnno!=null;
    }

    /**
     * 获取某个类上的Bean注解，可能是注解上有Bean注解
     * @param clz
     * @return
     */
    public static Annotation getBeanAnno(Class<?> clz){
        Annotation annotation = getAnnotation(clz, Bean.class);
        if(annotation != null){
            return annotation;
        }
        Annotation[] annotations = clz.getAnnotations();
        for(Annotation anno:annotations){
            Class<? extends Annotation> aClass = anno.annotationType();
            Annotation beanAnno = getAnnotation(aClass, Bean.class);
            if(beanAnno!=null){
                return anno;
            }
        }
        return null;
    }

    /**
     * 注解取值
     * @param annotation
     * @param fieldName
     * @return
     * @throws SpringException
     */
    public static Object getAnnoFieldValue(Annotation annotation,String fieldName) throws SpringException {
        try{
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
            memberValues.setAccessible(true);
            Map<String,Object> map = (Map<String, Object>) memberValues.get(invocationHandler);
            if(map.containsKey(fieldName)){
                return map.get(fieldName);
            }
        }catch (Exception e){
            throw new SpringException(IExceptionType.REFLECT_ERROR,"反射错误",e);
        }
        throw new SpringException(IExceptionType.REFLECT_ERROR,"反射错误,注解不存在"+fieldName+"属性");
    }

    /**
     * 获取bean的Name
     * @param clz
     * @return
     * @throws SpringException
     */
    public static String getBeanName(Class<?> clz) throws SpringException {
        Annotation beanAnno = getBeanAnno(clz);
        String name = StringUtil.toString(getAnnoFieldValue(beanAnno, "name"));
        if(StringUtil.isBlackOrNull(name)){
            String value = StringUtil.toString(getAnnoFieldValue(beanAnno, "value"));
            if(StringUtil.isBlackOrNull(value)){
                return getDefaultBeanName(clz);
            }
            return value;
        }
        return name;
    }

    /**
     * 获取默认的beanName
     * @param clz
     * @return
     */
    public static String getDefaultBeanName(Class<?> clz) {
        String simpleName = clz.getSimpleName();
        return simpleName.substring(0,1).toLowerCase()+simpleName.substring(1);
    }

    /**
     * 实例化bean
     * @param clz
     * @return
     */
    public static Object newBean(Class<?> clz) throws SpringException {
        try {
            return clz.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new SpringException(IExceptionType.REFLECT_ERROR,"实例化Bean异常",e);
        }
    }
}
