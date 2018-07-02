package com.samton.code.core.bean;

public class DbColumnAttribute {
    // 获得指定列的列名
    private String columnName;
    // 获得指定列的数据类型
    private Integer columnType;
    // 获得指定列的数据类型名
    private String columnTypeName;
    // 所在的Catalog名字
    private String catalogName;
    // 对应数据类型的类
    private String columnClassName;
    // 在数据库中类型的最大字符个数
    private Integer columnDisplaySize;
    // 默认的列的标题
    private String columnLabel;
    // 获得列的模式
    private String schemaName;
    // 某列类型的精确度(类型的长度)
    private Integer precision;
    // 小数点后的位数
    private Integer scale;
    // 获取某列对应的表名
    private String tableName;
    // 描述
    private String detail;
    // 驼峰字段名
    private String attrName;
    // 分页查询字段名（包含search.）
    private String searchName;
    // 查询类型 0：普通查询 1：范围查询
    private Integer searchType;
    // 查询控制长度
    private Integer fieldLength;
    // 是否自动递增
    private Boolean isAutoInctement;
    // 在数据库中是否为货币型
    private Boolean isCurrency;
    // 是否为空
    private Integer isNullable;
    // 是否为只读
    private Boolean isReadOnly;
    // 能否出现在where中
    private Boolean isSearchable;
    // 是否列中显示
    private Boolean isColumn;
    // 是否表单显示
    private Boolean isForm;

    public String getColumnName() {
	return columnName;
    }

    public void setColumnName(String columnName) {
	this.columnName = columnName;
    }

    public Integer getColumnType() {
	return columnType;
    }

    public void setColumnType(Integer columnType) {
	this.columnType = columnType;
    }

    public String getColumnTypeName() {
	return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
	this.columnTypeName = columnTypeName;
    }

    public String getCatalogName() {
	return catalogName;
    }

    public void setCatalogName(String catalogName) {
	this.catalogName = catalogName;
    }

    public String getColumnClassName() {
	return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
	this.columnClassName = columnClassName;
    }

    public Integer getColumnDisplaySize() {
	return columnDisplaySize;
    }

    public void setColumnDisplaySize(Integer columnDisplaySize) {
	this.columnDisplaySize = columnDisplaySize;
    }

    public String getColumnLabel() {
	return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
	this.columnLabel = columnLabel;
    }

    public String getSchemaName() {
	return schemaName;
    }

    public void setSchemaName(String schemaName) {
	this.schemaName = schemaName;
    }

    public Integer getPrecision() {
	return precision;
    }

    public void setPrecision(Integer precision) {
	this.precision = precision;
    }

    public Integer getScale() {
	return scale;
    }

    public void setScale(Integer scale) {
	this.scale = scale;
    }

    public String getTableName() {
	return tableName;
    }

    public void setTableName(String tableName) {
	this.tableName = tableName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public Boolean getIsAutoInctement() {
	return isAutoInctement;
    }

    public void setIsAutoInctement(Boolean isAutoInctement) {
	this.isAutoInctement = isAutoInctement;
    }

    public Boolean getIsCurrency() {
	return isCurrency;
    }

    public void setIsCurrency(Boolean isCurrency) {
	this.isCurrency = isCurrency;
    }

    public Integer getIsNullable() {
	return isNullable;
    }

    public void setIsNullable(Integer isNullable) {
	this.isNullable = isNullable;
    }

    public Boolean getIsReadOnly() {
	return isReadOnly;
    }

    public void setIsReadOnly(Boolean isReadOnly) {
	this.isReadOnly = isReadOnly;
    }

    public Boolean getIsSearchable() {
	return isSearchable;
    }

    public void setIsSearchable(Boolean isSearchable) {
	this.isSearchable = isSearchable;
    }

    public Boolean getIsColumn() {
        return isColumn;
    }

    public void setIsColumn(Boolean isColumn) {
        this.isColumn = isColumn;
    }

    public Boolean getIsForm() { return isForm; }

    public void setIsForm(Boolean form) { isForm = form; }
}
