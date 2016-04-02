/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoFactoryh2;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.DaoPacienteh2;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.util.Properties;

/**
 *
 * @author hcadavid
 */
public class PersistenceTest {

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    public PersistenceTest() {
    }

    @Before
    public void setUp() {
    }
    
    /*
    @Test
    public void pacienteConMasDeUnaConsultaMyBatis() throws PersistenceException, IOException{
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof = DaoFactory.getInstance(properties);
        
        DaoPaciente daopaciente = daof.getDaoPaciente();
        Paciente p = new Paciente(1019129303, "CC", "Juan Camilo", java.sql.Date.valueOf("1997-04-10"));
        Consulta c1 = new Consulta(java.sql.Date.valueOf("2016-03-05"), "Consulta general");
        Consulta c2 = new Consulta(java.sql.Date.valueOf("2016-04-10"), "Presenta un cuadro viral");
        p.getConsultas().add(c1);
        p.getConsultas().add(c2);
        
        daopaciente.save(p);
        Paciente p2 = daopaciente.load(1019129303, "CC");      
        assertTrue("Los pacientes no son iguales con mybatis",p.equals(p2));
                
        
        sqlss.close();

        
    }
    
    @Test
    public void pacienteConUnaConsultaMyBatis() throws IOException, PersistenceException{
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
       
        DaoFactoryh2 daof=new DaoFactoryh2();
        
        DaoPacienteh2 nuevodao = daof.getDaoPaciente();
        Paciente paciente = new Paciente(1019093806, "CC","jairo",java.sql.Date.valueOf("1994-04-10"));
        Consulta consul = new Consulta(java.sql.Date.valueOf("2016-03-15"), "Infeccion");
        
        paciente.getConsultas().add(consul);
        nuevodao.save(paciente);
        Paciente pacienteDos = nuevodao.load(1019093806,"CC");
        assertTrue("El paciente no quedo guardado con myBatis",paciente.equals(pacienteDos));
        
        sqlss.close();
        
    }
    @Test
    public void pacienteSinConsultas() throws IOException, PersistenceException{
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
        DaoFactoryh2 daof= new DaoFactoryh2();
        
        
        DaoPacienteh2 daoPaciente = daof.getDaoPaciente();
        
        Paciente p = new Paciente(2103021, "CC", "Juan Camilo", java.sql.Date.valueOf("1997-04-10"));
        
        daoPaciente.save(p);
        
        Paciente p2 = daoPaciente.load(2103021, "CC");
        
        assertTrue("No se pudo guardar el paciente con My baties",p.equals(p2));
        
        sqlss.close();
    }

    
 */   
}
