package com.samton.code.core.bean;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * @Description: 代码生成信息
 * @author: Alex
 * @date: 2017/8/9 10:43
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class    CreateFileProperty {
    private String tableName;
    private String tableNameZN;
    private String packageUrl;
    private String moduleName;
    private String authorName;
    private List<Integer> createFileType;
    private List<Integer> createMethodType;
    private List<DbColumnAttribute> columns;

    public CreateFileProperty(){ }

    public CreateFileProperty(String tableName, String tableNameZN, String packageUrl, String moduleName, String createFileType, String createMethodType, String columns, String authorName) {
        this.tableName = tableName;
        this.tableNameZN = tableNameZN;
        this.packageUrl = packageUrl;
        this.moduleName = moduleName;
        this.authorName = authorName;
        this.createFileType = new ArrayList<Integer>(0);
        for (String str : createFileType.split(",")) {
            if (str != null && !"".equals(str.toString().trim())){
                this.createFileType.add(Integer.parseInt(str));
            }
        }
        this.createMethodType = new ArrayList<Integer>(0);
        for (String str : createMethodType.split(",")) {
            if (str != null && !"".equals(str.toString().trim())){
                this.createMethodType.add(Integer.parseInt(str));
            }
        }
        
        this.columns = (List<DbColumnAttribute>) JSONArray.parseArray(columns, DbColumnAttribute.class);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameZN() {
        return tableNameZN;
    }

    public void setTableNameZN(String tableNameZN) {
        this.tableNameZN = tableNameZN;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthorName() { return authorName; }

    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public List<Integer> getCreateFileType() {
        return createFileType;
    }

    public void setCreateFileType(List<Integer> createFileType) {
        this.createFileType = createFileType;
    }

    public List<Integer> getCreateMethodType() {
        return createMethodType;
    }

    public void setCreateMethodType(List<Integer> createMethodType) {
        this.createMethodType = createMethodType;
    }

    public List<DbColumnAttribute> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumnAttribute> columns) {
        this.columns = columns;
    }
}
