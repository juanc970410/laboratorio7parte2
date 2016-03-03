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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.util.Date;
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
    CLASES DE EQUIVALENCIA
    
    1.El pasiente no se encuentre registrado en el sistema con ese id
    2.Que el pasiente que se va a registrar no sea con un id que este en el sistema y sea de otro pasiente
    3.El id con el que se registra el pasiente sea un entero
    4.El tipo de id que se ingresa sea una cadena
    5.Los datos con los que se registra el paciente sean correctos.
*/
public class PacientesTest {
    
    public PacientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroPacienteTest(){
        Paciente paciente = new Paciente(1019093806, "Cedula", "Jairo Gonzalez Boada", null);
        ServiciosPacientesStub servicio = new ServiciosPacientesStub();
        try {
            servicio.registrarNuevoPaciente(paciente);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(PacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
