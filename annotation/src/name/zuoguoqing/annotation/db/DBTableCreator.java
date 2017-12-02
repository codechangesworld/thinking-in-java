/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.annotation.db
 * @file DBTableCreator.java
 * @author ZuoGuoqing
 * @date 2017年11月29日 下午2:26:08
 * @version 1.0
 */
package name.zuoguoqing.annotation.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import name.zuoguoqing.annotation.Constraints;
import name.zuoguoqing.annotation.DBInteger;
import name.zuoguoqing.annotation.DBString;
import name.zuoguoqing.annotation.DBTableName;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月29日 下午2:26:08
 */
public class DBTableCreator {

    /**
     * @param args
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
        String className = "name.zuoguoqing.model.Member";
        String cmd = createTabeSqlCmd(className);
        
        System.out.println("Creating database table for " + className + " is:\n" + cmd);
        
    }
      
    /**
     * 根据数据库注解及数据模型类名构造建表SQL语句
     * @param className 类名
     * @return SQL语句
     * @throws ClassNotFoundException
     */
    public static String createTabeSqlCmd(String className) throws ClassNotFoundException {
        Class<?> cls = Class.forName(className);
        
        // table name
        DBTableName tableNameAnnotiation = cls.getAnnotation(DBTableName.class);
        if (tableNameAnnotiation == null) {
            System.out.println("No TableName annotiation found in " + className);
            return null;
        }
        String tableName = tableNameAnnotiation.name();
        if (tableName.length() < 1) {
            tableName = cls.getSimpleName().toUpperCase();
        }
        
        // each column
        List<String> columns = new ArrayList<>();
        for (Field field : cls.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            String fieldName = field.getName();
            if (annotations.length < 1) {
                System.out.println("No Annotations found in field " + fieldName);
            }
            
            // integer 
            if (annotations[0] instanceof DBInteger) {
                DBInteger annotation = (DBInteger)annotations[0];
                String name = annotation.name();
                if (name.length() < 1) {
                    name = fieldName.toUpperCase();
                }
                String constraints = getConstraints(annotation.constraints());
                columns.add(name + " INT" +  constraints);
            }
            
            // string
            if (annotations[0] instanceof DBString) {
                DBString annotation = (DBString)annotations[0];
                String name = annotation.name();
                if (name.length() < 1) {
                    name = fieldName.toUpperCase();
                }
                int length = annotation.value();
                String constraints = getConstraints(annotation.constraints());
                columns.add(name + " VARCHAR(" + length + ")" + constraints);
            }
        }
        
        // 
        StringBuilder cmd = new StringBuilder();
        cmd.append("CREATE TABLE ").append(tableName).append(" (");
        for (String column : columns) {
            cmd.append("\n\t").append(column).append(",");
        }
        cmd.deleteCharAt(cmd.length() -1).append("\n);");
        
        return cmd.toString();
    }

    /**
     * 获取字段约束字符串
     * @param constraints
     * @return
     */
    /**
     * @param constraints
     * @return
     */
    private static String getConstraints(Constraints constraints) {
        StringBuilder result = new StringBuilder();
        if (constraints.primaryKey()) {
            result.append(" PRIMARY KEY");
        }
        if (!constraints.allowNull()) {
            result.append(" NOT NULL");
        }
        if (constraints.unique()) {
            result.append(" UNIQUE");
        }

        return result.toString();
    }

}
