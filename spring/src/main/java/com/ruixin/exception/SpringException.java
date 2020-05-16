package com.ruixin.exception;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:统一错误处理类
 */
public class SpringException extends Throwable{

    public SpringException(int type,String message){
        super(message);
    }

    public SpringException(int type,String message,Throwable e){
        super(message,e);
    }

}
