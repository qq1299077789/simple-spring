package com.ruixin.exception;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:错误类型
 */
public interface IExceptionType {

    //bean错误处理
     int BEAN_ERROR = 1;

    //配置错误
    int CONFIG_ERROR = 2;

    //反射错误
    int REFLECT_ERROR = 3;
}
