/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Gabriel
 */
//Crea clase abstracta con modelo para que todo dao que tenga que crear extienda de DAO
//Las operaciones van a ser implementadas es sus subclases txt y sql
// Crea un tipo de dato T que es una entidad, es abstracto todavia no se que es.
//T K son genericos, tipos de datos abstractos.
// K identificaria un registro de tipo T
public abstract class DAO<T,K> {
    // CRUD (Create Read Update Delete)
    //Modelo para recibir datos
    //void xq no deberia devovler nada
    public abstract void create(T entidad) throws DAOException;
    // Me va a devolver, leer T por su id, todavia no se cual es
    public abstract T read(K id) throws DAOException;
    // Actualiza un registro de tipo T, en base a su id
    public abstract void update(T entidad) throws DAOException;
    // Actualiza por clave primaria, no devuelve nada,
    public abstract void delete(K id) throws DAOException;
    // Dame todos los registros, sin parametros xq quiero todos, en una lista
    public abstract List<T> findAll() throws DAOException;
    // Me dice si existe un registro por la clave primaria
    
    /**
     * Método que devuelve todos las entidades
     * @param all true: recupera todos (A/B/M) false: solo los activos (A/M)
     * @return
     * @throws DAOException 
     */
    //metodo para recupera todos los registros.
    public abstract List<T> findAll(boolean all) throws DAOException;
    // Me dice si existe un registro por la clave primaria
    public abstract boolean existe(K id) throws DAOException;
    //crea el metodo para cerrar la conexion
    public abstract void close() throws DAOException;
}
