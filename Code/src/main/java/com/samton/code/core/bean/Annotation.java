package com.samton.code.core.bean;

/**
 * 
 * @Description:类注释设置
 * @author: shenchu
 * @date: 2017年4月13日 上午9:07:34 Copyright (c) 2017, Samton. All rights reserved
 */
public class Annotation {
    // 类描述
    private String description;

    // 作者
    private String authorName;

    // 日期 格式：2017年2月27日 上午11:15:55
    private String date;

    // 授权说明
    private String copyright;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getAuthorName() {
	return authorName;
    }

    public void setAuthorName(String authorName) {
	this.authorName = authorName;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getCopyright() {
	return copyright;
    }

    public void setCopyright(String copyright) {
	this.copyright = copyright;
    }

}