package com.samton.code.core.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.samton.code.core.bean.BaseDB;
import com.samton.code.core.bean.DbColumnAttribute;

public class DbColumnUtil extends BaseDB {
    public List<DbColumnAttribute> getDbColumnByTableName(String tableName, String key) {
	List<DbColumnAttribute> list = new ArrayList<DbColumnAttribute>(0);

	try {
	    PreparedStatement stmt = getStmt(tableName, key);
	    if (stmt == null) {
		return list;
	    }
	    ResultSet rs = stmt.executeQuery();
	    PreparedStatement detailStmt = getDetailStmt(tableName);
	    ResultSet detailRs = detailStmt.executeQuery();
	    ResultSetMetaData data = rs.getMetaData();

	    while (rs.next()) {
		for (int i = 1; i <= data.getColumnCount(); i++) {
		    DbColumnAttribute column = new DbColumnAttribute();
		    if (detailRs.next()) {
			if (detailRs.getString("comment") != null
				&& !"".equals(detailRs.getString("comment"))) {
			    column.setDetail(detailRs.getString("comment")
				    .trim().replace("\n", " "));
			}
		    }
		    // 获得指定列的列名
		    column.setColumnName(data.getColumnName(i));
		    // 获得指定列的数据类型
		    column.setColumnType(data.getColumnType(i));
		    // 获得指定列的数据类型名
		    column.setColumnTypeName(data.getColumnTypeName(i));
		    // 所在的Catalog名字
		    column.setCatalogName(data.getCatalogName(i));
		    // 对应数据类型的类
		    column.setColumnClassName(data.getColumnClassName(i));
		    // 在数据库中类型的最大字符个数
		    column.setColumnDisplaySize(data.getColumnDisplaySize(i));
		    // 默认的列的标题
		    column.setColumnLabel(data.getColumnLabel(i));
		    // 获得列的模式
		    column.setSchemaName(data.getSchemaName(i));
		    // 某列类型的精确度(类型的长度)
		    column.setPrecision(data.getPrecision(i));
		    // 小数点后的位数
		    column.setScale(data.getScale(i));
		    // 获取某列对应的表名
		    column.setTableName(data.getTableName(i));
		    // 是否自动递增
		    column.setIsAutoInctement(data.isAutoIncrement(i));
		    // 在数据库中是否为货币型
		    column.setIsCurrency(data.isCurrency(i));
		    // 是否为空
		    column.setIsNullable(data.isNullable(i));
		    // 是否为只读
		    column.setIsReadOnly(data.isReadOnly(i));
		    // 能否出现在where中
		    column.setIsSearchable(data.isSearchable(i));

		    list.add(column);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return list;
    }

}
