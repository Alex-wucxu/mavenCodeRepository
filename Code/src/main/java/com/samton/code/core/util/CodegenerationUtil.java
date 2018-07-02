package com.samton.code.core.util;

import freemarker.template.Template;

import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.samton.code.core.bean.Annotation;
import com.samton.code.core.bean.BaseDB;
import com.samton.code.core.bean.ClassBean;
import com.samton.code.core.bean.CreateFileProperty;
import com.samton.code.core.bean.DbColumnAttribute;
import com.samton.code.core.constant.CodegenerationConstant;

/**
 * @Description: 代码生成工具类
 * @author: Alex
 * @date: 2017/8/15 16:26
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class CodegenerationUtil extends BaseDB {
    private static String tablename = "";
    private static String[] colnames;
    private static String[] colTypes;
    private static int[] colSizes; // 列名大小
    private static int[] colScale; // 列名小数精度
    private static String[] colDetail;//列名说明
    private static boolean importUtil = false;
    private static boolean importSql = false;
    private static boolean importMath = false;
    private static Template template;
    private static Writer writer;
    @Autowired 
    private static FreeMarkerConfigurer configurer;
  
    public void setConfigurer(FreeMarkerConfigurer configurer) {  
        this.configurer = configurer;  
    }

    /**
     * 生成JspView
     * @param cfp
     * @throws Exception
     */
    public static void createJspView(CreateFileProperty cfp) throws  Exception {
        createJsp(cfp, "viewJsp.ftl", null, "view");
    }

    /**
     * 生成JspUpdate
     * @param cfp
     * @throws Exception
     */
    public static void createJspUpdate(CreateFileProperty cfp) throws  Exception {
        createJsp(cfp, "updateJsp.ftl", "updateJs.ftl", "update");
    }

    /**
     * 生成JspAdd
     * @param cfp
     * @throws Exception
     */
    public static void createJspAdd(CreateFileProperty cfp) throws  Exception {
        createJsp(cfp, "addJsp.ftl", "addJs.ftl", "Add");
    }

    /**
     * 生成JspManage
     * @param cfp
     * @throws Exception
     */
    public static void createJspManage(CreateFileProperty cfp) throws Exception {
        createJsp(cfp, "manageJsp.ftl", "manageJs.ftl", "Manage");
    }

    /**
     * 生成Jsp和Js总方法
     * @param cfp
     * @throws Exception
     */
    public static void createJsp(CreateFileProperty cfp, String jspFtl, String jsFtl, String className) throws Exception {
        //创建注释内容
        Annotation annotation = new Annotation();
        //创建类内容
        ClassBean classBean = new ClassBean();
        //设置驼峰参数名
        setAttrColumnName(cfp);
        classBean.setModelName(cfp.getModuleName());
        classBean.setVoName(cfp.getModuleName() + "VO");
        classBean.setClassName(cfp.getModuleName().substring(0, 1).toLowerCase() + cfp.getModuleName().substring(1) + className);

        if (jspFtl != null) {
            //配置manageJsp
            classBean.setPackageName("views/" + cfp.getModuleName().substring(0, 1).toLowerCase() + cfp.getModuleName().substring(1));
            createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JSP_PATH + jspFtl);
        }

        if (jsFtl != null) {
            //配置manageJs
            classBean.setPackageName("script/" + cfp.getModuleName().substring(0, 1).toLowerCase() + cfp.getModuleName().substring(1));
            createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JSP_PATH + jsFtl);
        }
    }

    /**
     * 生成Dao层
     * @param cfp
     * @throws Exception
     */
    public static void createDao(CreateFileProperty cfp) throws Exception {
        //创建注释内容
        Annotation annotation = new Annotation();
        //创建类内容
        ClassBean classBean = new ClassBean();
        //设置驼峰参数名
        setAttrColumnName(cfp);

        //配置mapper接口
        annotation.setDescription("接口说明");
        classBean.setClassName(cfp.getModuleName().substring(0, 1).toUpperCase() + cfp.getModuleName().substring(1) + "Mapper");
        classBean.setPackageName(cfp.getPackageUrl() + ".dao");
        classBean.setVoName(cfp.getModuleName() + "VO");
        classBean.setVoPackageName(cfp.getPackageUrl() + ".bean.vo");
        classBean.setModelName(cfp.getModuleName());
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "iMapper.ftl");

        //配置mapperImp实现类
        annotation.setDescription("接口说明");
        classBean.setClassName(cfp.getModuleName().substring(0, 1).toUpperCase() + cfp.getModuleName().substring(1) + "Mapper");
        classBean.setPackageName(cfp.getPackageUrl() + ".dao.mapping");
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "mapperImpl.ftl");
    }

    /**
     * 生成Service层
     * @param cfp
     * @throws Exception
     */
    public static void createService(CreateFileProperty cfp) throws Exception {
        //创建注释内容
        Annotation annotation = new Annotation();
        //创建类内容
        ClassBean classBean = new ClassBean();

        //配置service接口
        annotation.setDescription("接口说明");
        classBean.setClassName("I" + cfp.getModuleName() + "Service");
        classBean.setPackageName(cfp.getPackageUrl() + ".service");
        classBean.setVoName(cfp.getModuleName() + "VO");
        classBean.setVoPackageName(cfp.getPackageUrl() + ".bean.vo");
        classBean.setModelName(cfp.getModuleName());
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "iService.ftl");

        //配置serviceImp实现类
        annotation.setDescription("接口说明");
        classBean.setClassName(cfp.getModuleName() + "ServiceImpl");
        classBean.setPackageName(cfp.getPackageUrl() + ".service.impl");
        classBean.setiServiceName("I" + cfp.getModuleName() + "Service");
        classBean.setiServicePackage(cfp.getPackageUrl() + ".service");
        classBean.setServiceName(cfp.getModuleName() + "Service");
        classBean.setMapperPackage(cfp.getPackageUrl() + ".dao");
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "serviceImpl.ftl");
    }

    /**
     * 生成Controller层
     * @param cfp
     * @throws Exception
     */
    public static void createController(CreateFileProperty cfp) throws Exception {
        //创建注释内容
        Annotation annotation = new Annotation();
        //创建类内容
        ClassBean classBean = new ClassBean();
        //设置驼峰参数名
        setAttrColumnName(cfp);

        //配置controller
        annotation.setDescription("控制类说明");
        classBean.setModelName(cfp.getModuleName());
        classBean.setClassName(cfp.getModuleName() + "Controller");
        classBean.setPackageName(cfp.getPackageUrl() + ".controller");
        classBean.setModuleUrl("/api/" + cfp.getModuleName().substring(0, 1).toLowerCase() + cfp.getModuleName().substring(1));
        classBean.setiServiceName("I" +cfp.getModuleName() + "Service");
        classBean.setiServicePackage(cfp.getPackageUrl()+ ".service");
        classBean.setVoName(cfp.getModuleName() + "VO");
        classBean.setVoPackageName(cfp.getPackageUrl() + ".bean.vo");
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "controller.ftl");
    }

    /**
     * 生成VO
     * @param cfp
     * @throws Exception
     */
    public static void createVo(CreateFileProperty cfp) throws Exception {
        //创建注释内容
        Annotation annotation = new Annotation();
        //创建类内容
        ClassBean classBean = new ClassBean();

        // 创建实体
        createEntity(cfp);

        //配置VO
        annotation.setDescription("VO类说明");
        classBean.setClassName(humpName(initcap(cfp.getModuleName())) + "VO");
        classBean.setPackageName(cfp.getPackageUrl() + ".bean.vo");
        classBean.setExtendName(humpName(initcap(cfp.getTableName())));
        classBean.setExtendPackage(cfp.getPackageUrl() + ".bean.entity");
        //classBean.setExtendPackage(PACKAGE_URL+".bean.entity");
        createFile(annotation, classBean, cfp, CodegenerationConstant.FTLS_JAVA_PATH + "classVO.ftl");
    }

    /**
     * 生成实体
     * @param cfp
     */
    private static void createEntity(CreateFileProperty cfp) {
        try {
            tablename = cfp.getTableName();
            int size = cfp.getColumns().size(); // 共有多少列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            colScale = new int[size];
            colDetail = new String[size];
            for (int i = 0; i < size; i++) {
                DbColumnAttribute column = cfp.getColumns().get(i);
                colDetail[i] = column.getDetail();

                colnames[i] = column.getColumnName().toLowerCase();
                colTypes[i] = column.getColumnTypeName().toLowerCase();
                colScale[i] = column.getScale();
                if ("datetime".equals(colTypes[i]) || "date".equals(colTypes[i])) {
                    importUtil = true;
                }
                if ("image".equals(colTypes[i]) || "text".equals(colTypes[i])) {
                    importSql = true;
                }
                if(colScale[i]>0){
                    importMath = true;
                }
                colSizes[i] = column.getPrecision();
            }
            String content = parse(cfp.getPackageUrl() + ".bean.entity");
            try {
                File filePath = new File(CodegenerationConstant.SAVE_URL + cfp.getModuleName() + "/" + cfp.getPackageUrl().replace(".", "/") + "/bean/entity");
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
//                FileWriter fw = new FileWriter(CodegenerationConstant.SAVE_URL + cfp.getModuleName() + "/" + cfp.getPackageUrl().replace(".", "/") + "/bean/entity/" + initcap(tablename) + ".java");
                File file = new File(CodegenerationConstant.SAVE_URL + cfp.getModuleName() + "/" + cfp.getPackageUrl().replace(".", "/") + "/bean/entity/" + humpName(initcap(tablename)) + ".java");
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
                PrintWriter pw = new PrintWriter(writer);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @Title:        myJavaFile
     * @Description:  生成java模块
     * @param:        @param 注释bean
     * @param:        @param 类bean
     * @param:        @param 模版
     * @param:        @throws Exception
     * @return:       void
     * @author        shenchu
     * @Date          2017年4月13日 下午2:01:50
     */
    public static void createFile(Annotation annotation, ClassBean classBean, CreateFileProperty cfp, String ftlName) throws Exception {
        // 加载模板文件
        template = configurer.getConfiguration().getTemplate(ftlName, "UTF-8");

        //创建数据模型
        Map<String, Object> root = new HashMap<String, Object>();
        //生成的作者
        annotation.setAuthorName(cfp.getAuthorName());
        // 生成的时间
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        annotation.setDate(format2.format(new Date()));
        annotation.setCopyright(CodegenerationConstant.ANT_COPYRIGHT);
        root.put("annotation", annotation);
        root.put("classBean", classBean);
        root.put("cfp", cfp);

        String beanPath = CodegenerationConstant.SAVE_URL + cfp.getModuleName() + "/" + classBean.getPackageName().replace(".", "/") + "/";
        File filePath = new File(beanPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        String filePathOfBean = "";
        if (ftlName.indexOf("mapperImpl.ftl") > 0) {
            filePathOfBean = beanPath + classBean.getClassName() + ".xml";
        } else if (ftlName.indexOf("Js.ftl") > 0) {
            filePathOfBean = beanPath + classBean.getClassName() + ".js";
        } else if (ftlName.indexOf("Jsp.ftl") > 0) {
            filePathOfBean = beanPath + classBean.getClassName() + ".jsp";
        } else {
            filePathOfBean = beanPath + classBean.getClassName() + ".java";
        }
        File file = new File(filePathOfBean);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 显示生成的数据
//        writer = new FileWriter(file);
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        template.process(root, writer);

        writer.flush();
        writer.close();
    }

    /**
     * 解析处理(生成实体类主体代码)
     */
    private static String parse(String packageUrl) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\npackage " + packageUrl + ";\r\n");
        sb.append("\r\nimport java.io.Serializable;\r\n");
        if (importUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (importSql) {
            sb.append("import java.sql.*;\r\n\r\n");
        }
        if(importMath){
            sb.append("import java.math.*;\r\n\r\n");
        }
        //表注释
        processColnames(sb);
        sb.append("public class ").append(humpName(initcap(tablename))).append(" implements Serializable {\r\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 处理列名,把空格下划线'_'去掉,同时把下划线后的首字母大写
     * 要是整个列在3个字符及以内,则去掉'_'后,不把"_"后首字母大写.
     * 同时把数据库列名,列类型写到注释中以便查看,
     */
    private static void processColnames(StringBuffer sb) {
        sb.append("\r\n/** ").append(tablename).append("\r\n");
        String colsiz;
//        String colsca = "";
        for (int i = 0; i < colnames.length; i++) {
            colsiz = colSizes[i] <= 0 ? "" : (colScale[i] <= 0 ? "("+colSizes[i]+")" : "(" + colSizes[i] + "," + colScale[i] + ")");
            sb.append("\t").append(colnames[i].toUpperCase()).append("	").append(colTypes[i].toUpperCase()).append(colsiz).append("\r\n");
            colnames[i] = humpName(colnames[i]);
        }
        sb.append("*/\r\n");
    }

    /**
     * 将字段名转换成驼峰命名
     */
    private static String humpName(String column){
        String str = "";
        char[] ch = column.toCharArray();
        char c;
        for(int i = 0; i < ch.length; i++){
            c = ch[i];
            if (c == '_') {
                if (ch[i + 1] >= 'a' && ch[i + 1] <= 'z') {
                    ch[i + 1] = (char) (ch[i + 1] - 32);
                }
            }
        }
        str = new String(ch);
        return str.replaceAll("_", "");
    }

    /**
     * 生成所有的方法
     *
     */
    private static void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set").append(initcap(colnames[i])).append("(");
	    if ("org.postgresql.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(postgreSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("com.mysql.jdbc.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(mysqlSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("oracle.jdbc.driver.OracleDriver".equals(prop.getProperty("driverClass").trim())) {
		sb.append(oracleSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    }
            sb.append(" ").append(colnames[i]).append("){\r\n");
            sb.append("\t\tthis.").append(colnames[i]).append("=").append(colnames[i]).append(";\r\n");
            sb.append("\t}\r\n");

            sb.append("\tpublic ");
	    if ("org.postgresql.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(postgreSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("com.mysql.jdbc.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(mysqlSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("oracle.jdbc.driver.OracleDriver".equals(prop.getProperty("driverClass").trim())) {
		sb.append(oracleSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    }
            sb.append(" get").append(initcap(colnames[i])).append("(){\r\n");
            sb.append("\t\treturn ").append(colnames[i]).append(";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
     * 解析输出属性
     *
     */
    private static void processAllAttrs(StringBuffer sb) {
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\t/** \r\n");
            sb.append("\t * ").append(colDetail[i].replaceAll("\\s*", "")).append(" \r\n");
            sb.append("\t */\r\n");
            sb.append("\tprivate ");
	    if ("org.postgresql.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(postgreSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("com.mysql.jdbc.Driver".equals(prop.getProperty("driverClass").trim())){
		sb.append(mysqlSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    } else if ("oracle.jdbc.driver.OracleDriver".equals(prop.getProperty("driverClass").trim())) {
		sb.append(oracleSqlTypeJavaType(colTypes[i], colScale[i], colSizes[i]));
	    }
            sb.append(" ").append(colnames[i]).append(";\r\n");
        }
        sb.append("\r\n");
    }

    /**
     * 把输入字符串的首字母改成大写
     */
    private static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 设置驼峰命名
     */
    private static void setAttrColumnName(CreateFileProperty cfp) {
        List<DbColumnAttribute> newList = new ArrayList<DbColumnAttribute>(0);

        for (int i = 0; i < cfp.getColumns().size(); i++) {
            DbColumnAttribute column = cfp.getColumns().get(i);
            column.setAttrName(humpName(column.getColumnName()));
            column.setSearchName("search." + column.getAttrName());
            newList.add(column);
        }
        cfp.setColumns(newList);
    }
    
    public static void expZipFile(String module, HttpServletResponse response) throws IOException {
        System.out.println("打包文件开始。。。");
        //要打包文件路径
        String srcFilePath = CodegenerationConstant.SAVE_URL + module;
        //生成打包文件路径
        String zipFilePath = srcFilePath + "/" + module + ".zip";
        File zipFile = new File(zipFilePath);
        File srcdir = new File(srcFilePath);
        if(!srcdir.exists()){
            throw new RuntimeException(srcFilePath + "不存在！");
        }

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        zip.addFileset(fileSet);
        zip.execute();

        //将压缩包发送给客户端
        FileInputStream in = new FileInputStream(zipFilePath);
        response.reset();
        String fileName = module + ".zip";
        String contentType = "application/force-download;charset=UTF-8";
//        String recommendedName = new String(fileName.getBytes(),"ISO8859_1");
        String recommendedName = new String(fileName.getBytes(),"UTF-8");
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + recommendedName);
        OutputStream out = response.getOutputStream();
        byte[]  b = null;
        if(in.available()>0){
            int len = in.available();
            b = new byte[len];
            in.read(b);
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        in.close();
        //删除服务器端的压缩包包
        File file = new File(CodegenerationConstant.SAVE_URL + module);
        if(file.exists()){
            delFiles(file);
        }
        System.out.println("打包文件结束。。。");
    }

    // 删除文件（循环递归删除里面所有文件）
    private static void delFiles (File file) {
        if (file.isFile()) {
            File f = new File(file.getPath());
            f.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++){
                delFiles(files[i]);
            }
            file.delete();
        }
    }
}