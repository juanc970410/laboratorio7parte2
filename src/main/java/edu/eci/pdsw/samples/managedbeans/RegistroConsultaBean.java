/*
 * Copyright (C) 2016 hcadavid
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
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collection.*;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author hcadavid
 */

@SessionScoped
@ManagedBean(name = "RegistroConsultaBean")

public class RegistroConsultaBean implements Serializable {

    private String tipoId;
    private int id;
    private String nombre;
    private Date bornDate;
    private Date consultaDate;
    private Paciente pacienteSeleccionado= new Paciente(1094, "cc", "jairo", null);
    private List<Consulta> consultasporPaciente;
    private List<Paciente> pacientes = new LinkedList<Paciente>();
    
    public RegistroConsultaBean(){
        this.pacientes = sp.obtenerPacientes();
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    public String getTipoId() {
        return tipoId;
        
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
    
    public void agregar() throws ExcepcionServiciosPacientes{
        Paciente nPaciente = new Paciente(id, tipoId, nombre, new java.sql.Date(bornDate.getTime()));
        sp.registrarNuevoPaciente(nPaciente);
        pacientes = sp.obtenerPacientes();
    } 
    
    ServiciosPacientes sp=ServiciosPacientes.getInstance();
    

    public List<Consulta> getConsultasporPaciente() {
        Iterator<Consulta> iter ;
        iter = pacienteSeleccionado.getConsultas().iterator();
        consultasporPaciente=new ArrayList<Consulta>();
        for(int i=0; i<pacienteSeleccionado.getConsultas().size();i++){
            consultasporPaciente.add(iter.next());
        }
        
        return consultasporPaciente;
    }

    public void setConsultasporPaciente(List<Consulta> consultasporPaciente) {
        this.consultasporPaciente = consultasporPaciente;
    }
    public Paciente getPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }
    

    public Date getConsultaDate() {
        return consultaDate;
    }

    public void setConsultaDate(Date consultaDate) {
        this.consultaDate = consultaDate;
    }
    private String resumen;

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
