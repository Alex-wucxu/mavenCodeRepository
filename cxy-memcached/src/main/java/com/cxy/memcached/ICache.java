package com.cxy.memcached;

public interface ICache {
    
    //设置缓存
    boolean set(Object key, Object value);
    
    //删除缓存
    boolean remove(Object key);
    
    //获得缓存
    Object get(Object key);
    
    //获得缓存根据refresh来判断是否刷新缓存时间
    Object get(Object key,boolean refresh);
    
    //判断缓存是否存在
	boolean isAlive(String key);
	
	//关闭所有缓存
	boolean flush();
    
}