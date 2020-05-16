package com.ruixin.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:bean加载类
 */
public class ClassLoader {

    //存放class
    public List<Class<?>> classes = new ArrayList<Class<?>>();

    //获取加载class的集合
    public List<Class<?>> getClasses() {
        return classes;
    }



}
