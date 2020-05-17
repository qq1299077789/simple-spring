package com.ruixin.util;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:String工具类
 */
public class StringUtil {

    //判断String是否为空
    public static boolean isBlackOrNull(Object object){
        if(object == null){
            return true;
        }
        return object.equals("");
    }

    public static String toString(Object object){
        return object.toString();
    }
}
