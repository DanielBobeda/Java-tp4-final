/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class DAOAlumnoFactory {
    //declara todo privado para que no se puedan instanciar n objetos (solo 1)
    //Instance es el nombre que le asigno a la Factory
    private static DAOAlumnoFactory instance;
    
    //Crea las String que usa para llenar el mapa de txt
    //estos valores asignados luego seran pisados por los datos reales.
    //no se pueden instanciar varias veces, se pisan.
    public static final String TIPO_DAO = "TIPO_DAO";
    public static final String TIPO_DAO_TXT = "TIPO_DAO_TXT";
    public static final String FILE_NAME = "FILE_NAME";
    //Crea las String que usa para llenar el mapa del sql
    public static final String TIPO_DAO_SQL = "TIPO_DAO_SQL";
    public static final String URL_DB = "URL_DB";
    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";
    
    //constructor por defecto
    private DAOAlumnoFactory() {
    }
    //Metodo para crear un sigleton (1 sola instancia)
    //Metodo publico, si no fue instanciado lo instancia (crea)
    public static DAOAlumnoFactory getInstance() {
        if (instance==null) {
            instance = new DAOAlumnoFactory();
        }
        
        return instance;
    }
    
    /**
     * getDAO
     * @param config Mapa para setear el tipo de DAO y los parámetros relacionados a cada tipo
     * @return DAO (AlumnoDAOTXT o AlumnoDAOSQL)
     * @throws DAOAlumnoFactoryException 
     */
    //Crea metodo  que devuelve un DAO
    //Factory, capa intermedia entre el los llamadores y el DAO
    //El objeto mapa almacena la info clave y valor de string, lo llama config
    public DAO getDAO(Map<String, String> config) throws DAOAlumnoFactoryException {
        //Crea tipo que contiene el tipo de dao
        String tipo = config.get(TIPO_DAO);
        switch (tipo) {
            //si tipo es TIPO_DAO_TXT
            case TIPO_DAO_TXT:
            {
                try {
                    //crea un AlumnoDAOTXT, en el archivo que se creo en AlumnoDAOTXT
                    //config.get(FILE_NAME) devuelve el nombre del archivo. rws puede leer y escribir
                    return new AlumnoDAOTXT(new File(config.get(FILE_NAME)), "rws");
                } catch (AlumnoDAOException ex) {
                    //puede arrojar una exception
                    throw new DAOAlumnoFactoryException("No se pudo crear una instancia del DAO TXT ==> "+ex.getMessage());
                }
            }
            case TIPO_DAO_SQL:
            {//crea DAO_SQL con stings y las carga con, url, usuario y contraseña que se ingresarán
                String url = config.get(URL_DB);
                String user = config.get(USER);
                String pass = config.get(PASSWORD);
                try {//devuelve el constructor con los datos
                return new AlumnoDAOSQL(url, user, pass);
                } catch (AlumnoDAOException ex) {//o devuelve la exception con el mensaje pertinente
                    throw new DAOAlumnoFactoryException("No se pudo crear una instancia del DAO SQL ==> "+ex.getMessage());
                }
            }
            default:// si no es ninguno de los dos tipos, crea otra exception avisando que aun no existe ese tipo
                throw new DAOAlumnoFactoryException("Aún no implementado");
        }
    }
    
}
