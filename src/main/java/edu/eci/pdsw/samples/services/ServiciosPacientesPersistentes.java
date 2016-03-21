/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.jdbcimpl.JDBCDaoFactory;
import edu.eci.pdsw.samples.persistence.jdbcimpl.JDBCDaoPaciente;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanHerrera
 */
public class ServiciosPacientesPersistentes extends ServiciosPacientes{
    
    DaoFactory daof;
    
    public ServiciosPacientesPersistentes() throws IOException{
        InputStream input = null;
        input = ServiciosPacientes.class.getClassLoader().getResource("applicationconfig.properties").openStream();       
        Properties properties=new Properties();
        properties.load(input);
        daof = new JDBCDaoFactory(properties);
        
    }
    
    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
        Paciente p= null;
        try {
            daof.beginSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No inicio la coneccion", ex);
        }
        try {
            p= daof.getDaoPaciente().load(idPaciente, tipoid);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            daof.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No termino la coneccion", ex);
        }
        return p;
    }

    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
        try {
            daof.beginSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No inicio la coneccion", ex);
        }
        try {
            daof.getDaoPaciente().save(p);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            daof.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No termino la coneccion", ex);
        }
    }

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
        try {
            daof.beginSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No inicio la coneccion", ex);
        }
        try {
            Paciente p = daof.getDaoPaciente().load(idPaciente, tipoid);
            p.getConsultas().add(c);
            daof.getDaoPaciente().update(p);
            
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            daof.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No termino la coneccion", ex);
        }
    }

    @Override
    public List<Paciente> obtenerPacientes() throws ExcepcionServiciosPacientes{
        try {
            daof.beginSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No inicio la coneccion", ex);
        }
        List<Paciente> list = null;
        try {
            list = daof.getDaoPaciente().cargarPacientes();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosPacientesPersistentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            daof.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosPacientes("No termino la coneccion", ex);
        }
        return list;
    }
    
}
