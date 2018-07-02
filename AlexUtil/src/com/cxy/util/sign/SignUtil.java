package com.cxy.util.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cxy.util.md5.MD5Util;

public class SignUtil {

	/**
	 * 
	 * @Title:        getSign 
	 * @Description:  获得签名值
	 * @param:        @param map		参数集合
	 * @param:        @param apiKey		密钥
	 * @param:        @param orderType	加密方式	0：数字-字母升序	1：字母-数字降序	2：往中间排序A-M 数字-M	3：往两边排序M-A M-数字
	 * @param:        @param isHasAnd	在追加签名值得时候是否需要添加&符	false：不添加	true：添加
	 * @param:        @return    		签名值
	 * @return:       String    
	 * @author        Alex
	 * @throws 		Exception 
	 * @Date          2016年3月17日 下午4:52:26
	 */
	public static String getSign(Map<String, Object> map, String apiKey, Short orderType, Boolean isHasAnd) throws Exception{

		String result = "";
		//根据加密方式获得对应的签名
		switch (orderType) {
		case 0:
			result = getUpOrderUrl(map);
			break;
		case 1:
			result = getDownOrderUrl(map);
			break;
		case 2:
			result = getMiddleOrderUrl(map);
			break;
		case 3:
			result = getSeparateOrderUrl(map);
			break;
		}
        
		//与签名值之间是否存在&符
		if(isHasAnd){
			result += "&";
		}

		//追加密钥
    	result = result + apiKey;
        
    	//获得前面值
        result = MD5Util.getMD5String(URLEncoder.encode(result, "UTF-8"));
        return result;
	}
	
	/**
	 * 
	 * @Title:        getUpOrderUrl 
	 * @Description:  根据参数获得升序后的URL
	 * @param:        @param map
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2016年3月21日 上午9:50:04
	 */
	public static String getUpOrderUrl(Map<String, Object> map){
        
        //获得数组
        String [] arrayToSort = getOrderUrl(map);

        //进行排序
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        
        StringBuilder sb = new StringBuilder();
        
        //组装排序后的参数
        for(int i = 0; i < arrayToSort.length; i ++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
	}
	
	/**
	 * 
	 * @Title:        getDownOrderUrl 
	 * @Description:  根据参数获得降序后的URL
	 * @param:        @param map
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2016年3月21日 上午10:09:16
	 */
	public static String getDownOrderUrl(Map<String, Object> map){

        //获得数组
        String [] arrayToSort = getOrderUrl(map);
		
		return null;
	}
	
	/**
	 * 
	 * @Title:        getMiddleOrderUrl 
	 * @Description:  根据参数获得往中间排序A-M 数字-M后的URL
	 * @param:        @param map
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2016年3月21日 上午10:10:11
	 */
	public static String getMiddleOrderUrl(Map<String, Object> map){
		return null;
	}
	
	/**
	 * 
	 * @Title:        getSeparateOrderUrl 
	 * @Description:  根据参数获得往两边排序M-A M-数字后的URL
	 * @param:        @param map
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2016年3月21日 上午10:10:41
	 */
	public static String getSeparateOrderUrl(Map<String, Object> map){
		return null;
	}
	
	/**
	 * 
	 * @Title:        getOrderUrl 
	 * @Description:  根据参数追加拼接符
	 * @param:        @param map
	 * @param:        @return    
	 * @return:       List<String>    
	 * @author        Alex
	 * @Date          2016年3月21日 上午10:04:03
	 */
	private static String [] getOrderUrl(Map<String, Object> map){
		List<String> list = new ArrayList<String>();

		//拼接参数
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(!"".equals(entry.getValue())){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        
        int size = list.size();
        
        //进行排序
        String [] arrayToSort = list.toArray(new String[size]);
        
        return arrayToSort;
	}
}
