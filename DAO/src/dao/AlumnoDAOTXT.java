/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fecha.MiCalendarioException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//Trae a alumno dentro del paquete persona para usarlo aquí
import persona.Alumno;
import persona.Persona;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
//Alumnodaotxt extiende de DAO
//Los tipos de datos que voy a manejar en DAO son Alumno e integer su tipo de clave primaria (dni)
//Le indico que remplace T por alumno y K por un entero (dni)
public class AlumnoDAOTXT extends DAO<Alumno, Integer>{
    //Crea raf de TIPO RandomAccessFile, posiciona al principio
    private RandomAccessFile raf;
    //voy a trabajar en un archivo de texto
    //Creo el constructor, con Archivo que es de tipo File
    //Se quito el public del constructor para que no se pueda acceder desde afuera, solo a traves del Factory
    AlumnoDAOTXT(File archivo, String rws) throws AlumnoDAOException {
        try {//rodea la sentencia con un try cach por si no encuentra el archivo
//Instancia raf,es es un RandomAccesFile 
//el primero es el file "archivo" y es el modo de escritura rws impacta de una en el disco
            raf = new RandomAccessFile(archivo, "rws");
        } catch (FileNotFoundException ex) {
            //Crea una excepcion  con el texto"" y el el mensaje de la exception
            throw new AlumnoDAOException("Archivo no encontrado ==> "+ex.getMessage());
        }
    }
// Implementa todos los metodos abstractos que tiene CRUD       
    @Override // Implementa todos los metodos abstractos que tiene CRUD   
                // crea alu de tipo alumno
    public void create(Alumno alu) throws AlumnoDAOException {
        //llama al metodo existe y al getDni
        if (existe(alu.getDni())) {//si existe da la exception
            throw new AlumnoDAOException("El alumno con DNI "+alu.getDni()+" ya existe");
        }
        
        try {
            //alu.setEstado('A'); Si se quiere setear el estado en A sin importar lo q ingreso el usuario 
            // Posicionar al final, donde se ingresa cada registro
            raf.seek(raf.length());
            // Posicionar al inicio, si se quiere elmas nuevo arriba
            //raf.seek(0);
            // Escribo el registro ingresado, lo pasa a to String
            raf.writeBytes(alu+System.lineSeparator());
            
        } catch (IOException ex) {
            throw new AlumnoDAOException("Error E/S ==> "+ex.getMessage());
        }
    }

    @Override
    //Lee
    public Alumno read(Integer dni) throws AlumnoDAOException {
        try {
            // Posicionar al inicio
            raf.seek(0);
            //Crea linea y campos
            String linea;
            String[] campos;
            //mientras linea sea distinto de  null
            while ((linea = raf.readLine())!=null) {
                //split permite dividir una cadena
                //Corta a persona en los DELIM
                campos = linea.split(Persona.DELIM);
                //si el campo 0 tomado del txt es igual a dni
                if (Integer.valueOf(campos[0]).equals(dni)) {
                    //el alu de tipo alumno es igual al metodo string2alumno desde el campo de ese alumno
                    Alumno alu = Alumno.string2Alumno(campos);
                    //el alu de tipo alumno es igual al metodo string2alumno desde el campo de ese alumno
                    if (alu.getEstado()!='B') {
                        return alu;
                    }
                    //si no esta devuelve null
                    return null;
                }
            }//podría estar algo mal solo si se modifico el txt manualmente
        } catch (IOException ex) {
            throw new AlumnoDAOException("Error E/S ==> "+ex.getMessage());
        } catch (PersonaException | MiCalendarioException ex) {
            throw new AlumnoDAOException("Error al formar el alumno ==> "+ex.getMessage());
        }
        return null;
    }

    /**
     * Este método actualiza un alumno activo
     * El parámetro alu se utiliza para pisar la información actual
     */
    @Override
    public void update(Alumno alu) throws AlumnoDAOException {
        try {
            // Posicionar al inicio
            raf.seek(0);
            //Crea linea y campos
            String linea;
            String campos[];
            //mientras linea sea distinto de  null
            while ((linea = raf.readLine())!=null) {
                //actualiza el puntero en cada vuelta
                long puntero = raf.getFilePointer();
                //split permite dividir una cadena
                //Corta a persona en los DELIM
                campos = linea.split(Persona.DELIM);
                //si el campo 0 tomado del txt es igual a dni
                if (Integer.valueOf(campos[0]).equals(alu.getDni())) {
                    //raf.seek(puntero -66); //66 es el total de la linea
                    raf.seek(puntero -linea.length()-2);//length -2 me da 66 (el tamaño de la linea)                    
                    alu.setEstado('M');
                    raf.writeBytes(alu+System.lineSeparator());
                }
            }//podría estar algo mal solo si se modifico el txt manualmente
        } catch (IOException ex) {
            throw new AlumnoDAOException("Error E/S ==> "+ex.getMessage());
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        // getFilePointer() en raf
        // Condición: alumno activo
        // estado en M
    }

    @Override
    public void delete(Integer dni) throws AlumnoDAOException {
        // Borrado lógico - Marcarlo como borrado
        // Leer el alumno y setear el estado en 'B'
        
        /*1 ERROR - USA UPDATE PERO DEJA EL ESTADO EN M
        Alumno alu = read(dni);
        try {
            alu.setEstado('B');
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        update(alu);
        //1*/
        
        ///*2 ENTREGA EN EL TP ANTERIOR - Cumple pero repite lineas de código
        try {
            // Posicionar al inicio
            raf.seek(0);
            //Crea linea y campos
            String linea;
            String campos[];
            //mientras linea sea distinto de  null
            while ((linea = raf.readLine())!=null) {
                //split permite dividir una cadena
                //Corta a persona en los DELIM
                long puntero = raf.getFilePointer();
                campos = linea.split(Persona.DELIM);
                //si el campo 0 tomado del txt es igual a dni
                if (Integer.valueOf(campos[0]).equals(dni)) {
                    //el alu de tipo alumno es igual al metodo string2alumno desde el campo de ese alumno
                    Alumno alu = Alumno.string2Alumno(campos);
                    //raf.seek(puntero -66); //66 es el total de la linea
                    raf.seek(puntero -linea.length()-2);//length -2 me da 66 (el tamaño de la linea)
                    alu.setEstado('B');
                    raf.writeBytes(alu+System.lineSeparator());
                }
            }//podría estar algo mal solo si se modifico el txt manualmente
        } catch (IOException ex) {
            throw new AlumnoDAOException("Error E/S ==> "+ex.getMessage());
        } catch (PersonaException | MiCalendarioException ex) {
            throw new AlumnoDAOException("Error al formar el alumno ==> "+ex.getMessage());
        }
       //2*/
    }

    @Override
    public boolean existe(Integer dni) throws AlumnoDAOException {
        try {
            // Posicionar al inicio
            raf.seek(0);
            String linea;//crea linea
            //redline toma toda una linea en string y lo guarda en linea
            //lee mientras hay algo, sea distinto de null
            while ((linea = raf.readLine())!=null) {
                //Substring 0-8 toma los primeros 8, el dni
                //valueof cambia el tipo de linea de sting para compararla con dni
                //con .equals compara si es igual a dni
                if (Integer.valueOf(linea.substring(0, 8)).equals(dni)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            throw new AlumnoDAOException("Error E/S ==> "+ex.getMessage());
        }
        //si existe devuelve true, si no devuelve false y si hay problemas la exception       
        return false;
    }

    @Override
    public List<Alumno> findAll() throws DAOException {
        ///*1 - FORMA PEDIDA - llama al metodo boolean en false
        //llamo al findall con el boolean el false.
        return findAll(false);
        //*/
        
        /*2 - FORMA ENTREGADA EN EL 1° TP
        //crea una lista
          List<Alumno> alumnos = new ArrayList<>();
        try {//posiciona al inicio lo recorre mientras sea distinto de nullo
            raf.seek(0);
            String linea;
            String campos[];
            while ((linea = raf.readLine()) != null) {
                //divide persona
                campos = linea.split(Persona.DELIM);
                //
                if(campos[campos.length-1].equals("A") || campos[campos.length-1].equals("M"))
                    //Si es igual a alta agrega al alumno a la lista con el metodo string2Alumno
                    alumnos.add(Alumno.string2Alumno(campos));
                    System.out.println("Alumno de la lista ==> "+alumnos);

            }
        } catch (IOException | PersonaException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }
        return alumnos;
        //2*/
    }

    @Override
    public List<Alumno> findAll(boolean all) throws DAOException {
       
       ///*1 - FORMA DADA EN CLASE
        //crea una lista
          List<Alumno> alumnos = new ArrayList<>();
        try {//posiciona al inicio lo recorre mientras sea distinto de nullo
            raf.seek(0);
            String linea;
            String campos[];
            while ((linea = raf.readLine()) != null) {
                //divide persona
                campos = linea.split(Persona.DELIM);
                Alumno alu=Alumno.string2Alumno(campos);
    //si all es igual a true o estado es distinto de B lo agrego
                if (all || alu.getEstado()!='B') {
                alumnos.add(alu);
                System.out.println("Alumno de la lista ==> "+alumnos);
                }
            }
        } catch (IOException | PersonaException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }        
        return alumnos;
        //1*/
       
        /*2 - FORMA ENTREGADA EN EL TP
        //crea una lista
          List<Alumno> alumnos = new ArrayList<>();
        try {//posiciona al inicio lo recorre mientras sea distinto de nullo
            raf.seek(0);
            String linea;
            String campos[];
            while ((linea = raf.readLine()) != null) {
                //divide persona
                campos = linea.split(Persona.DELIM);
                    //Si es igual a alta agrega al alumno a la lista con el metodo string2Alumno
                    alumnos.add(Alumno.string2Alumno(campos));
                    System.out.println("Alumno de la lista ==> "+alumnos);
            }
        } catch (IOException | PersonaException | MiCalendarioException ex) {
            throw new DAOException(ex.getMessage());
        }        
        return null;
        //2*/
    }

    @Override//para cerrar el archivo
    public void close() throws DAOException {
        try {//si raf es distinto de null
            if (raf!=null) {
                //cierrra el archivo
                raf.close();
            }
        } catch (IOException ex) {
            throw new DAOException("Error al cerrar archivo ==> "+ex.getMessage());
        }
    }
    
}
