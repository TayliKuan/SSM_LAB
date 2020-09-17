/*
 * This class not only produces the global org.hibernate.SessionFactory reference in its static initializer;
 * it also hides the fact that it uses a static singleton
*/

package com.MybatisUtil;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBaitsUtil {

    private static final SqlSessionFactory sessionFactory;
   

    static {
     InputStream inputStream = null;    
     try {
      inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
      sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
     } catch (Throwable e) {
      throw new ExceptionInInitializerError(e);
     }finally {
           try {
            inputStream.close();
           } catch (IOException e) {
             // Intentionally ignore. Prefer previous error.
            System.err.println(e.getStackTrace());
           }
     
        }
    }

    public static SqlSessionFactory getSessionFactory() {
     return sessionFactory;
    }

}