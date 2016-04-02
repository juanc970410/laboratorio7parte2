/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author 2103021
 */
public class DaoFactoryh2 extends DaoFactory{
    
    
    
    private static Properties appProperties=null;
    SqlSessionFactory sqlsf = null;
    SqlSession sqls = null;
    
    public DaoFactoryh2() {
        
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlsf == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlsf = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    @Override
    public void beginSession() throws PersistenceException {
        sqls = sqlsf.openSession();
    }

    @Override
    public DaoPacienteh2 getDaoPaciente() {
        return new DaoPacienteh2(sqls);
    }

    @Override
    public void commitTransaction() throws PersistenceException {
        if (sqls!=null){
            sqls.commit();
        }
    }

    @Override
    public void rollbackTransaction() throws PersistenceException {
        if (sqls!=null){
            sqls.rollback();
        }
    }

    @Override
    public void endSession() throws PersistenceException {
        if(sqls!=null){
            sqls.close();
        }
    }
    
}
