/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personatest;

import fecha.MiCalendario;
import fecha.MiCalendarioException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.Persona;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class PersonaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Persona juan = new Persona();
            
            juan.setDni(4545454);
            juan.setApyNom("Juan");
            juan.setSexo('m');

/*            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, 40);
            cal.set(Calendar.MONTH, 3);
            cal.set(Calendar.YEAR, 2020);
  */          
            juan.setFechaNac(new MiCalendario(27, Calendar.APRIL, 2020));
            
            // dd/MM/yyyy
            //System.out.println("Fecha ==> "+);
            System.out.println("OK ==> DIA: "+
                    juan.getFechaNac().get(Calendar.DAY_OF_MONTH)+
                    " MES: "+juan.getFechaNac().get(Calendar.MONTH)+
                    " AÑO: "+juan.getFechaNac().get(Calendar.YEAR));
            /*
            Persona maria;
            try {
            maria = new Persona(10);
            
            System.out.println("TODO OK");
            } catch (PersonaException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            Persona pepe;
            try {
            pepe = new Persona(24004600, "  pepe Garcia  ", 'M');
            System.out.println("TODO OK");
            } catch (PersonaException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("TODO OK");
        */     
        } catch (PersonaException | MiCalendarioException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Alumno maria;
        Alumno pepe;
        Alumno juana;
        try {
            // Alumno
            maria = new Alumno(7.5, 18, 24004600, "Maria Garcia", 'F',
                        new MiCalendario(23, 8, 1974),'A',new MiCalendario(23, 4, 2017));

            pepe = new Alumno(4.5, 12, 24004601, "Pepe Garcia", 'm',
                        new MiCalendario(24, 9, 1978),'B',new MiCalendario(20, 4, 2018));

            juana = new Alumno(8d, 11, 4004602, "Juana Garcia", 'f',
                        new MiCalendario(28, 12, 1985),'M',new MiCalendario(15, 4, 2018));
        } catch (PersonaException | MiCalendarioException ex) {
            Logger.getLogger(PersonaTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        // dd/MM/yyyy
        System.out.println("Fecha Nac. ==> "+ maria.getFechaNac());
        System.out.println("Fecha Nac. ==> "+ pepe.getFechaNac());
        System.out.println("Fecha Nac. ==> "+ juana.getFechaNac());
        
        // Llenar lista de Alumnos
        List<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos.add(maria);
        alumnos.add(pepe);
        alumnos.add(juana);
        
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
    
}
