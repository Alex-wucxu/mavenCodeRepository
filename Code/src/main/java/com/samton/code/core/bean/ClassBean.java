/**
 * 
 */
package com.samton.code.core.bean;

/**
 * @Description:构造类内容
 * @author: wucxu
 * @date: 2017年4月13日 上午9:15:32 Copyright (c) 2017, Samton. All rights reserved
 */
public class ClassBean {
    // 模块名称
    private String modelName;
    // 包名称
    private String packageName;
    // 类名称
    private String className;
    // 继承类
    private String extendName;
    // 模块访问路径
    private String moduleUrl;
    // 继承类包
    private String extendPackage;
    // 实现类(可以为数组)
    private String implementName;
    // service接口
    private String iServiceName;
    // service接口包
    private String iServicePackage;
    // service名称
    private String serviceName;
    // 自定义vo名称
    private String voName;
    // 自定义vo包名称
    private String voPackageName;
    // mybatis配置mapper包
    private String mapperPackage;

    public String getPackageName() {
	return packageName;
    }

    public void setPackageName(String packageName) {
	this.packageName = packageName;
    }

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public String getExtendName() {
	return extendName;
    }

    public void setExtendName(String extendName) {
	this.extendName = extendName;
    }

    public String getImplementName() {
	return implementName;
    }

    public void setImplementName(String implementName) {
	this.implementName = implementName;
    }

    public String getExtendPackage() {
	return extendPackage;
    }

    public void setExtendPackage(String extendPackage) {
	this.extendPackage = extendPackage;
    }

    public String getiServiceName() {
	return iServiceName;
    }

    public void setiServiceName(String iServiceName) {
	this.iServiceName = iServiceName;
    }

    public String getiServicePackage() {
	return iServicePackage;
    }

    public void setiServicePackage(String iServicePackage) {
	this.iServicePackage = iServicePackage;
    }

    public String getModuleUrl() {
	return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
	this.moduleUrl = moduleUrl;
    }

    public String getServiceName() {
	return serviceName;
    }

    public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
    }

    public String getModelName() {
	return modelName;
    }

    public void setModelName(String modelName) {
	this.modelName = modelName;
    }

    public String getVoName() {
	return voName;
    }

    public void setVoName(String voName) {
	this.voName = voName;
    }

    public String getVoPackageName() {
	return voPackageName;
    }

    public void setVoPackageName(String voPackageName) {
	this.voPackageName = voPackageName;
    }

    public String getMapperPackage() {
	return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
	this.mapperPackage = mapperPackage;
    }

}
