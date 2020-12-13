/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dao.AlumnoDAOException;
import dao.AlumnoDAOTXT;
import dao.DAO;
import dao.DAOAlumnoFactory;
import dao.DAOAlumnoFactoryException;
import dao.DAOException;
import fecha.MiCalendario;
import fecha.MiCalendarioException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class TestDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //instancia la factory, crea si no existe
            DAOAlumnoFactory daf = DAOAlumnoFactory.getInstance();
            //implementa clase map, usa HashMap de tipo mapa
            //Si no implementa esta linea, liego no se pueden cargar con put
            Map<String, String> config = new HashMap();
            
/*//TIPO_DAO_TXT          llena el mapa en TIPO_DAO con TIPO_DAO_TXT
            //llena el mapa en FILE_NAME con el nombre del archivo a crear (si no existe)
            config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.TIPO_DAO_TXT);
            config.put(DAOAlumnoFactory.FILE_NAME, "alumno.txt");
            //*/
 
///*//TIPO_DAO_SQL          Carga los parametros necesarios para usar TIPO_DAO_SQL en DAOAlumnoFactory
            //tipo de dao TIPO_DAO_SQL (se diferencia en la factory del TIPO_DAO_TXT)
            config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.TIPO_DAO_SQL);
            //se almacena la url sin el usuario /el esquema efc creado - url: jdbc:mysql://127.0.0.1:3306/?user=root
            config.put(DAOAlumnoFactory.URL_DB, "jdbc:mysql://127.0.0.1:3306/efc?serverTimezone=UTC");
            config.put(DAOAlumnoFactory.USER, "root");
            config.put(DAOAlumnoFactory.PASSWORD, "1234");
            //*/
            
//pido un dao, de acuerdo a los parametros cargados arriba
            DAO<Alumno, Integer> dao = daf.getDAO(config);
//avisa si todo fue cargado correctamente
            System.out.println("CONNECTION OK");
            
            
            //Carga alu
            final MiCalendario fechaNac = new MiCalendario(11, 7, 1993);
            final MiCalendario fechaIng = new MiCalendario(7, 5, 2018);
            Alumno alu = new Alumno(5.2, 12, 22222222, "Elimina SQL2", 'm', fechaNac,'B',fechaIng);
            
            ///*//Crea el a alu
            dao.create(alu);
            System.out.println("Alumno cargado ==> "+dao.read(alu.getDni()));
            //*/
            
            /*//Actualiza el a alu
            dao.update(alu);
            System.out.println("Alumno modificado ==> "+dao.read(alu.getDni()));
            //*/
            
            /*//findall muestra todos los registros, menos los de baja
            dao.findAll();
            //*/
            
            /*//findall boolean true, muestra los registros  incluso los de ESTADO B (baja).
            dao.findAll(true);
            //*/
            
            /*///Elimina el a alu
            dao.delete(22222222);
            System.out.println("Alumno dado de baja");
            //Alumno aluRead = dao.read(89898989);
            //System.out.println("Alumno Eliminado==> "+aluRead);
            //*/
            
            /*// Read - lee un registro
            Alumno aluRead = dao.read(12345678);
            if (aluRead==null) {
                System.err.println("Alunmo no encontrado");
            }
            else if (dao.existe(12345678)) {
                System.out.println("OK Encontrado ==> "+aluRead);
            }
            //*/
            
            //Activar solo en modo SQL, cierra
            dao.close();
        } catch (DAOException | MiCalendarioException | PersonaException | DAOAlumnoFactoryException ex) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
