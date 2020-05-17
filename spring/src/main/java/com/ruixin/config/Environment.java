package com.ruixin.config;

import com.ruixin.exception.IExceptionType;
import com.ruixin.exception.SpringException;
import com.ruixin.util.StringUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:环境
 */
public class Environment {

    //bean的包
    private List<String> beanPackage = new ArrayList<String>();

    //配置文件
    private Properties properties;

    //默认加载的配置文件
    private String configFile = "rx.properties";

    //初始化
    public void init() throws SpringException {
        try{
            this.properties = new Properties();
            InputStream inputStream = Environment.class.getClassLoader().getResourceAsStream(configFile);
            properties.load(inputStream);
            merge();
        }catch (Exception e){
            throw new SpringException(IExceptionType.CONFIG_ERROR,"配置文件读取错误",e);
        }
    }

    /**
     * 合并配置
     */
    private void merge(){
        Map<String, String> getenv = System.getenv();
        this.properties.putAll(getenv);
    }

    public List<String> getBeanPackage() {
        return beanPackage;
    }

    public void setBeanPackage(List<String> beanPackage) {
        this.beanPackage = beanPackage;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public static Environment builder(String configFile) throws SpringException {
        Environment environment = new Environment();
        if(!StringUtil.isBlackOrNull(configFile)){
            environment.configFile = configFile;
        }
        environment.init();
        return environment;
    }
}
