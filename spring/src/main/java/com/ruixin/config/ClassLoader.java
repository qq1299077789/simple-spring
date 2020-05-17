package com.ruixin.config;

import com.ruixin.exception.SpringException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:bean加载类
 */
public class ClassLoader {

    //存放class
    public List<Class<?>> classes = new ArrayList<Class<?>>();

    //文件后缀
    private final String subffix = ".class";

    //获取加载class的集合
    public List<Class<?>> getClasses() {
        return classes;
    }

    /**
     * 加载类
     * @param packages
     * @throws SpringException
     */
    public void load(List<String> packages) throws SpringException {
        for (String pkg: packages) {
            pkg = pkg.replaceAll("\\.","/");
            URL resource = ClassLoader.class.getClassLoader().getResource(pkg);
            if(resource != null){
                File dir = new File(resource.getPath());
                loadClass(dir,pkg);
            }
        }
    }

    /**
     * 加载类
     * @param dir 目录
     * @param pkg 包名
     * @throws SpringException
     */
    public void loadClass(File dir,String pkg) throws SpringException {
        try{
            if(dir == null ||!dir.exists()||!dir.isDirectory()){
                return;
            }
            File[] files = dir.listFiles();
            for(File file:files){
                if(file.isDirectory()){
                    loadClass(file,pkg+"/"+file.getName());
                }else{
                    String name = file.getName();
                    if(name.endsWith(subffix)){
                        pkg = pkg.replaceAll("/","\\.");
                        classes.add(Class.forName(pkg+"."+name.substring(0,name.lastIndexOf("."))));
                    }
                }
            }
        }catch (Exception e){
            throw new SpringException(0,"加载class出错",e);
        }
    }

}
