package com.cxy.memcached.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class ResourceUtil {
	
	public static String getResourcePath(){
		String path = ResourceUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        int position = path.indexOf("WEB-INF/");
        if (position >= 0) {
            path = path.substring(0, position);
            path += "WEB-INF/classes";
            try {
				return URLDecoder.decode(path,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        return null;
	}
	
    public static String getResourceConfPath() {
    	String confPath=ResourceUtil.getResourcePath();
    	if(confPath==null)return null;
    	return confPath;
    }

}