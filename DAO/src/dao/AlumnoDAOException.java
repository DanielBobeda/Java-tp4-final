/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Gabriel
 */
//Alumno DAOException, extiende de DAOexception, se lanza cuando un alumno usa el CRUD
public class AlumnoDAOException extends DAOException {

    public AlumnoDAOException(String mensaje) {
        super(mensaje);
    }
    
}
