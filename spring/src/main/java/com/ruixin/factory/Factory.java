package com.ruixin.factory;

import com.ruixin.exception.IExceptionType;
import com.ruixin.exception.SpringException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Ruixin on 2020/5/16.
 * Description:工厂对象
 */
public abstract class Factory<K,V> {

    //工厂容器
    private HashMap<K,V> factory = new HashMap<K, V>();

    /**
     * 设置值（检查是否唯一）
     * @param k
     * @param v
     */
    public void set(K k,V v) throws SpringException{
        if(isValid(k,v)){
            factory.put(k,v);
        }else{
            throw new SpringException(IExceptionType.BEAN_ERROR,"工厂键值不唯一");
        }
    }

    /**
     * 设置值，不检查是否唯一
     * @param k
     * @param v
     */
    public void setIgnoreValid(K k,V v){
        factory.put(k, v);
    }

    /**
     * 取值
     * @param k
     * @return
     */
    public V get(K k){
        return factory.get(k);
    }

    /**
     * 取大小
     * @return
     */
    public int size(){
        return factory.size();
    }

    /**
     * 取键值
     * @return
     */
    public Set<K> keySet(){
        return factory.keySet();
    }

    /**
     * 取值列表
     * @return
     */
    public Collection<V> values(){
        return factory.values();
    }

    /**
     * 检查K是否唯一
     * @param k
     * @param v
     * @return
     */
    public boolean isValid(K k,V v){
        if(factory.containsKey(k)) {
            if(v == factory.get(k)){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

}
