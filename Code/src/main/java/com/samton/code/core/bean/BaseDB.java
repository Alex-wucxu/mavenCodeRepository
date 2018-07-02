package com.samton.code.core.bean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.samton.code.core.util.AesUtils;

public class BaseDB {
    protected static Connection conn = null;
    protected static Properties prop;

    private static void getConn(String key) {
	prop = new Properties();
	InputStream in = null;
	try {
	    in = BaseDB.class.getClassLoader().getResourceAsStream("/properties/codegeneration.properties");
	    prop.load(in);
	    String driver = prop.getProperty("driverClass").trim();
	    String url = prop.getProperty("jdbcUrl").trim();
	    String username = new String(AesUtils.decrypt(AesUtils.parseHexStr2Byte(prop.getProperty("user").trim()), key));
	    String password = new String(AesUtils.decrypt(AesUtils.parseHexStr2Byte(prop.getProperty("password").trim()), key));

	    Class.forName(driver);// 加载驱动程序，此处运用隐式注册驱动程序的方法
	    conn = DriverManager.getConnection(url, username, password);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (null != in) {
		    in.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * postgresp
     * 
     * @param sqlType
     * @param scale
     * @return
     */
    protected static String postgreSqlTypeJavaType(String sqlType, int scale, int size) {
	return getSqlType2JavaTypeByDBType(sqlType, scale, size);
    }

    /**
     * Oracle
     * 
     * @param sqlType
     * @param scale
     * @return
     */
    protected static String oracleSqlTypeJavaType(String sqlType, int scale, int size) {
	return getSqlType2JavaTypeByDBType(sqlType, scale, size);
    }

    /**
     * Mysql
     * 
     * @param sqlType
     * @param scale
     * @return
     */
    protected static String mysqlSqlTypeJavaType(String sqlType, int scale, int size) {
	return getSqlType2JavaTypeByDBType(sqlType, scale, size);
    }

    /**
     * 根据数据库类型获得对应的JAVA类型
     * 
     * @param sqlType
     * @param scale
     * @param size
     * @return
     */
    private static String getSqlType2JavaTypeByDBType(String sqlType, int scale, int size) {
	if ("serial".equals(sqlType.toLowerCase()) 
		|| "long".equals(sqlType.toLowerCase())) {
	    return "Long";
	} else if ("bigint".equals(sqlType.toLowerCase())){
	    return "Long";
	} else if ("smallint".equals(sqlType.toLowerCase())){
	    return "Short";
	} else if ("int".equals(sqlType.toLowerCase())){
	    return "Integer";
	} else if ("integer".equals(sqlType.toLowerCase())) {
	    return "Integer";
	} else if ("int4".equals(sqlType.toLowerCase()) 
		|| "int8".equals(sqlType.toLowerCase())) {
	    return "Integer";
	} else if ("int2".equals(sqlType.toLowerCase())) {
	    return "Short";
	} else if ("tinyint".equals(sqlType.toLowerCase())) {
	    return "Short";
	} else if ("float".equals(sqlType.toLowerCase()) 
		|| "float precision".equals(sqlType.toLowerCase())
		|| "double".equals(sqlType.toLowerCase())
		|| "double precision".equals(sqlType.toLowerCase())) {
	    return "BigDecimal";
	} else if ("number".equals(sqlType.toLowerCase()) 
		|| "decimal".equals(sqlType.toLowerCase())
		|| "numeric".equals(sqlType.toLowerCase()) 
		|| "real".equals(sqlType.toLowerCase())) {
	    return scale == 0 ? (size < 10 ? "Integer" : "Long") : "BigDecimal";
	} else if ("varchar".equals(sqlType.toLowerCase()) 
		|| "varchar2".equals(sqlType.toLowerCase())
		|| "char".equals(sqlType.toLowerCase()) 
		|| "nvarchar".equals(sqlType.toLowerCase())
		|| "nchar".equals(sqlType.toLowerCase())) {
	    return "String";
	} else if ("datetime".equals(sqlType.toLowerCase()) 
		|| "date".equals(sqlType.toLowerCase())
		|| "timestamp".equals(sqlType.toLowerCase())) {
	    return "Date";
	}
	return null;
    }

    protected PreparedStatement getStmt(String tableName, String key) {
	PreparedStatement table_stmt = null;
	try {
	    getConn(key);
	    ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null);
	    if (!rs.next()) {
		return null;
	    }
	    String strsql = "SELECT * FROM " + tableName + " limit 1";// +" WHERE ROWNUM=1"
								      // 读一行记录;
	    table_stmt = conn.prepareStatement(strsql);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return table_stmt;
    }

    protected PreparedStatement getDetailStmt(String tableName) {
	PreparedStatement table_detail_stmt = null;
	try {
	    StringBuffer table_detail_sql = new StringBuffer();
	    if ("org.postgresql.Driver".equals(prop.getProperty("driverClass").trim())){
		table_detail_sql.append(" SELECT a.attnum,");
		table_detail_sql.append(" a.attname AS field,");
		table_detail_sql.append(" t.typname AS type,");
		table_detail_sql.append(" a.attlen AS length,");
		table_detail_sql.append(" a.atttypmod AS lengthvar,");
		table_detail_sql.append(" a.attnotnull AS notnull,");
		table_detail_sql.append(" b.description AS comment");
		table_detail_sql.append(" FROM pg_class c,");
		table_detail_sql.append(" pg_attribute a");
		table_detail_sql.append(" LEFT OUTER JOIN pg_description b ON a.attrelid=b.objoid AND a.attnum = b.objsubid,");
		table_detail_sql.append(" pg_type t");
		table_detail_sql.append(" WHERE c.relname = '" + tableName + "'");
		table_detail_sql.append(" and a.attnum > 0");
		table_detail_sql.append(" and a.attrelid = c.oid");
		table_detail_sql.append(" and a.atttypid = t.oid");
		table_detail_sql.append(" ORDER BY a.attnum");
	    } else if ("com.mysql.jdbc.Driver".equals(prop.getProperty("driverClass").trim())){
		table_detail_sql.append(" SELECT COLUMN_NAME,");
		table_detail_sql.append(" column_comment AS comment");
		table_detail_sql.append(" FROM INFORMATION_SCHEMA.Columns");
		table_detail_sql.append(" WHERE table_name='" + tableName + "'");
		table_detail_sql.append(" AND table_schema='" + prop.getProperty("dbName").trim() + "'");
	    }
	    table_detail_stmt = conn.prepareStatement(table_detail_sql.toString());
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return table_detail_stmt;
    }
}
