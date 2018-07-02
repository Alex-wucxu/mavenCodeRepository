package com.cxy.util.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class BeanUtil {
	
	/**
	 * 
	 * @Title:        setBeanParameters 
	 * @Description:  复制对象内部参数
	 * @param:        @param oldBean	原始对象
	 * @param:        @param newBean	新对象
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2016年6月24日 下午6:17:28
	 */
	public void setBeanParameters(Object oldBean, Object newBean) throws Exception{
		
		//内省获得对象
		BeanInfo bean = Introspector.getBeanInfo(oldBean.getClass(), Object.class);
		
		//获得对象中所有属性
		PropertyDescriptor[] pds = bean.getPropertyDescriptors();
	}

}
