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
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */

/*
  Clase de equivalencia:
    1. El paciente esta registrado
    2. El paciente no esta registracdo
    3. El paciente ya tiene la consulta que se le está tratando de agregar
    4. El paciene no tiene la consulta a agregar
*/
public class ConsultasTest {
    
    public ConsultasTest() {
    }
    
    
    @Test
    public void registroConsultaAPacienteTest() throws ExcepcionServiciosPacientes{
        boolean paso = false;
        Paciente p = new Paciente(1234, "Cedula", "Nicolas", null);
        ServiciosPacientes sp = new ServiciosPacientesStub();
        int idp = p.getId();
        String tip = p.getTipo_id();
        Consulta c = new Consulta(null, "gripa");
        sp.registrarNuevoPaciente(p);
        try {
            sp.agregarConsultaAPaciente(idp, tip, c);
            paso=true;
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(ConsultasTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue("No se hizo correctamente la verificacion del paciente",paso);
    }
    
    
}
