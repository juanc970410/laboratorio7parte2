/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.mybatis.mappers.PacienteMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author 2103021
 */
public class DaoPacienteh2 implements DaoPaciente{
    SqlSession sqlsession = null;
    PacienteMapper mapper = null;
    
    public DaoPacienteh2(SqlSession sqls){
        this.sqlsession = sqls;
        mapper = sqlsession.getMapper(PacienteMapper.class);
    }
    @Override
    public Paciente load(int id, String tipoid) throws PersistenceException {
        Paciente p = mapper.loadPacienteById(id, tipoid);
        if (p==null){
            throw new PersistenceException("el paciente no existe");
        }
        return p;
    }

    @Override
    public void save(Paciente p) throws PersistenceException {
        if (mapper.loadPacienteById(p.getId(), p.getTipo_id())==null){
            mapper.insertPaciente(p);
            for (Consulta c : p.getConsultas()){
                mapper.insertConsulta(c, p.getId(), p.getTipo_id());
                c.setId(1);
            }
        }else{
            throw new PersistenceException("el paciente ya esta guardado");
        }
        
    }

    @Override
    public void update(Paciente p) throws PersistenceException {
        if(mapper.loadPacienteById(p.getId(), p.getTipo_id())!=null){
            for (Consulta c : p.getConsultas()){
                if(c.getId()==-1){
                    mapper.insertConsulta(c, p.getId(), p.getTipo_id());
                    c.setId(1);
                }
            }
        }
    }

    @Override
    public List<Paciente> cargarPacientes() throws PersistenceException {
        return mapper.cargaPacientes();
    }
    
}
