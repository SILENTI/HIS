package com.util;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

/**
 * Mybatis获取SqlSession
 * */
public class MybatisUtil {


    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

    private static SqlSessionFactory factory;

    static {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("Mybatis/config/mybatis-config.xml");
        factory = builder.build(input);
    }


    public static SqlSession getSqlSession(){
        return factory.openSession();
    }

    public static SqlSession getSqlSession(Boolean b){
        return factory.openSession(b);
    }

    public static <T>T getMapper (Class clazz){
        return (T) factory.openSession().getMapper(clazz);
    }

    public static <T>T getMapper (Class clazz,Boolean judge){
        return (T) factory.openSession(judge).getMapper(clazz);
    }
}
